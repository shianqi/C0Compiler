package com.imudges.C0Compiler.Executer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by yangy on 2016/12/19.
 */
public class Execute {
    //当前层数基地址
    private int baseAddress=0;
    // 当前执行指令的标号
    private int currentIndex=0;
    //指令名
    private enum DictateName{
        LIT, LOD, STO, CAL, INT, JMP, JPC,
        ADD, SUB, MUL, DIV, RED, WRT, RET
    }
    //指令
    private class Dictate{
       private DictateName dictateName;
       private int t;
       private int a;
    }
    private Scanner scanner=new Scanner(System.in);
    private ArrayList<Dictate> dictateList=new ArrayList<>();
    private Stack<Integer> runStack=new Stack<>();

    /**
     * LIT 0 a
     * 将常数值取到栈顶
     * @param t 0
     * @param a a
     */
    private void dictate_LIT(int t,int a){
        runStack.push(a);
    }

    /**
     * LOD t a
     * 将变量值
     * @param t 层数
     * @param a 相对地址
     */
    private void dictate_LOD(int t,int a){
        if(t==0){
            runStack.push(runStack.get(a));
        }else{
            runStack.push(runStack.get(a+baseAddress));
        }
    }

    /**
     * STO t a
     * 将栈顶内容送入某变量单元中
     * @param t 层数
     * @param a 相对地址
     */
    private void dictate_STO(int t,int a){
        if(t==0){

            runStack.set(a, runStack.pop());
        }else{
            //不是0层，变量单元地址就是当前层的基地址+相对地址
            runStack.set(baseAddress + a, runStack.pop());
        }
    }

    /**
     * int 0 a
     * 在运行栈中为被调用的过程开辟a个单元的数据区
     * @param t 0
     * @param a 函数地址
     */
    private void dictate_CAL(int t,int a){
        //修改DL 和 RL
        int base = runStack.size();
        runStack.push(baseAddress);
        runStack.push(currentIndex+1);
        //更新当前层数基地址
        //更新当前执行指令的标号
        baseAddress = base;
        currentIndex = a - 1;
    }
    /**
     * int 0 a
     * 在运行栈中为被调用的过程开辟a个单元的数据区
     * @param t 0
     * @param a 数据区数量
     */
    private void dictate_INT(int t, int a){
        for(int i=0;i<a;i++){
            runStack.push(0);
            if(runStack.size()>=500){
                //System.out.println("栈满500");
                Error.ShowErrMsg(4,"所在行数为:第"+currentIndex+"行");
            }
        }
    }
    /**
     * JMP 0 a
     * 无条件跳转至a地址
     * @param t 0
     * @param a 跳转到的地址
     */
    private void dictate_JMP(int t, int a){
        currentIndex = a - 1;
    }

    /**
     * JPC 0 a
     * 条件跳转，当栈顶值为0，则跳转至a地址，否则顺序执行
     * @param t 0
     * @param a 跳转地址
     */
    private void dictate_JPC(int t, int a){
        if(runStack.peek()==0){
            currentIndex = a - 1;
        }
    }

    /**
     * ADD 0 0
     * 次栈顶与栈顶相加，退两个栈元素，结果值进栈
     * @param t 0
     * @param a 0
     */
    private void dictate_ADD(int t, int a){
        int val_a = runStack.pop();
        int val_b = runStack.pop();
        runStack.push(val_a+val_b);
    }
    /**
     * SUB 0 0
     * 次栈顶减去栈顶，退两个栈元素，结果值进栈
     * @param t 0
     * @param a 0
     */
    private void dictate_SUB(int t, int a){
        int val_a = runStack.pop();
        int val_b = runStack.pop();
        runStack.push( val_b-val_a);
    }

    /**
     * MUL 0 0
     * 次栈顶乘以栈顶，退两个栈元素，结果值进栈
     * @param t 0
     * @param a 0
     */
    private void dictate_MUL(int t, int a){
        int val_a = runStack.pop();
        int val_b = runStack.pop();
        runStack.push(val_b*val_a);
    }
    /**
     * DIV
     * 次栈顶除以栈顶，退两个栈元素，结果值进栈
     * @param t 0
     * @param a 0
     */
    private void dictate_DIV(int t, int a){
        int val_a = runStack.pop();
        if(val_a==0){
            Error.ShowErrMsg(1,"所在行数为:第"+currentIndex+"行");
        }
        int val_b = runStack.pop();
        runStack.push( val_b/val_a);
    }

    /**
     * RED 0 0
     * 从命令行读入一个输入置于栈顶
     * @param t 0
     * @param a 0
     */
    private void dictate_RED(int t, int a){

        runStack.push(scanner.nextInt());
    }

    /**
     * wrt 0 0
     * 栈顶值输出至屏幕并换行
     * @param t 0
     * @param a 0
     */
    private void dictate_WRT(int t, int a){
        if(runStack.empty()){
            //System.out.println("读入栈失败，栈为空");
            Error.ShowErrMsg(4,"所在行数为:第"+currentIndex+"行");
        }else{
            System.out.println(runStack.peek());
        }
    }

    /**
     * ret 0 0
     * 函数调用结束后,返回调用点并退栈
     * 如果当前为0层，则运行结束
     * @param t 0
     * @param a 0
     */
    private void dictate_RET(int t, int a){
        if(baseAddress==0){
            currentIndex = 999999;
            return;
        }
        //将要return的值存在一个临时变量中
        //把当前层所有都退栈
        //再把return的值放在栈顶
        int returnValue = runStack.peek();
        currentIndex = runStack.get(baseAddress+1)-1;
        int baseAddressTemp = baseAddress;
        baseAddress = runStack.get(baseAddress);
        while(runStack.size()>baseAddressTemp){
            runStack.pop();
        }
        runStack.push(returnValue);
    }
    /**
     * 初始化函数
     */
    void init(){
        System.out.println("input the C0Target file name");
        readFileByLines(scanner.next());
        execute();
    }
    /**
     * 执行函数
     */
    private void execute(){
        while(currentIndex<dictateList.size()){
            if(!Error.errFlag){
                analyse(currentIndex);
                currentIndex++;
            }else {
                return;
            }
        }
    }
    /**
     * 指令分析
     * @param index 指令编号
     */
    private void analyse(int index){
        int t = dictateList.get(index).t;
        int a = dictateList.get(index).a;
        switch (dictateList.get(index).dictateName){
            case LIT : dictate_LIT(t,a); break;
            case LOD : dictate_LOD(t,a); break;
            case STO : dictate_STO(t,a); break;
            case CAL : dictate_CAL(t,a); break;
            case INT : dictate_INT(t,a); break;
            case JMP : dictate_JMP(t,a); break;
            case JPC : dictate_JPC(t,a); break;
            case ADD : dictate_ADD(t,a); break;
            case SUB : dictate_SUB(t,a); break;
            case MUL : dictate_MUL(t,a); break;
            case DIV : dictate_DIV(t,a); break;
            case RED : dictate_RED(t,a); break;
            case WRT : dictate_WRT(t,a); break;
            case RET : dictate_RET(t,a); break;
        }
    }

    /**
     * 读取文件信息
     * @param fileName 文件名字
     */
    private void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(!tempString.equals("")){
                    String[] str = tempString.split(" ");
                    Dictate dictate = new Dictate();
                    for(DictateName e: DictateName.values()){
                        if(e.toString().equals(str[0])){
                            dictate.dictateName = e;
                            break;
                        }
                    }
                    dictate.t = Integer.parseInt(str[1]);
                    dictate.a = Integer.parseInt(str[2]);
                    dictateList.add(dictate);
                    System.out.println("line " + line + ": " + dictate.dictateName.toString()+ " " +
                            dictate.t + " " + dictate.a);
                    line++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    System.out.print("文档关闭失败");
                }
            }
        }
    }
}
