package com.imudges.C0Compiler.Executer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 执行类
 * Created by killer on 2016/11/1.
 */
public class Exe {
    enum Dic{
        LIT, LOD, STO, CAL, INT, JMP, JPC,
        ADD, SUB, MUL, DIV, RED, WRT, RET
    }
    class Dictate{
        public Dic dic;
        public int t;
        public int a;
    }
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Dictate> dictateList = new ArrayList<>();
    private Stack<Integer> runStack = new Stack<Integer>();

    /**
     * LIT 0 a
     * 将常数值取到栈顶
     * @param t 0
     * @param a 常数值
     */
    public void dic_LIT(int t, int a){

    }

    /**
     * LOD t a
     * 将变量值取到栈顶
     * @param t 层数
     * @param a 相对地址
     */
    public void dic_LOD(int t, int a){

    }

    /**
     * sto t a
     * 将栈顶内容送入某变量单元中
     * @param t 层数
     * @param a 相对地址
     */
    public void dic_STO(int t, int a){

    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_CAL(int t, int a){

    }

    /**
     * int 0 a
     * 在运行栈中为被调用的过程开辟a个单元的数据区
     * @param t 0
     * @param a 数据区数量
     */
    public void dic_INT(int t, int a){
        for(int i=0;i<a;i++){
            runStack.push(0);
            if(runStack.size()>=500){
                System.out.println("栈满500");
                return;
            }
        }
    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_JMP(int t, int a){

    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_JPC(int t, int a){

    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_ADD(int t, int a){

    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_SUB(int t, int a){

    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_MUL(int t, int a){

    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_DIV(int t, int a){

    }

    /**
     * red 0 0
     * 从命令行读入一个输入置于栈顶
     * @param t 0
     * @param a 0
     */
    public void dic_RED(int t, int a){
        if(runStack.empty()){
            System.out.println("读入栈失败，栈为空");
        }else{
            runStack.set(runStack.size()-1,scanner.nextInt());
        }
    }

    /**
     * wrt 0 0
     * 栈顶值输出至屏幕并换行
     * @param t 0
     * @param a 0
     */
    public void dic_WRT(int t, int a){
        if(runStack.empty()){
            System.out.println("读入栈失败，栈为空");
        }else{
            System.out.println(runStack.peek());
        }
    }

    /**
     *
     * @param t
     * @param a
     */
    public void dic_RET(int t, int a){

    }

    /**
     * 读取文件信息
     * @param fileName 文件名字
     */
    public void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(!tempString.equals("")){
                    String[] str = tempString.split(" ");
                    Dictate dictate = new Dictate();
                    for(Dic e:Dic.values()){
                        if(e.toString().equals(str[0])){
                            dictate.dic = e;
                            break;
                        }
                    }
                    dictate.t = Integer.parseInt(str[1]);
                    dictate.a = Integer.parseInt(str[2]);
                    dictateList.add(dictate);
                    System.out.println("line " + line + ": " + dictate.dic.toString()+ " " +
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

                }
            }
        }
    }
}
