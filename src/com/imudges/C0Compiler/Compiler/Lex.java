package com.imudges.C0Compiler.Compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by killer on 2016/10/18.
 * @author shianqi@imudges.com
 * c0编译器词法分析类
 */
public class Lex {
    //源程序单词缓存
    private String sourceCodeTemp;
    //行数
    private int row;
    //缓存单词字符指针
    private int index;
    //变量最大长度
    private int symMaxLength = 20;

    public Lex(){
        sourceCodeTemp = "";
        row = 0;
        index = 0;
        getSym();
    }

    public String[] reservedWord = {
            "main",
            "void",
            "if",
            "while",
            "return",
            "int",
    };

    public enum symbol
    {
        //---------------------- 基本字
        mainsym,    // 主程序
        voidsym,    // 空类型
        ifstasym,   // 如果语句
        whilestasym,// 循环语句
        resym,      // return
        intsym,     // 整型
        nul,        // 未知

        ifsym,      // if
        elsesym,    // else
        whilesym,   // while

        readstasym, // 读语句
        writestasym,// 写语句
        scanfsym,   // 输入
        printfsym,  // 输出
        //---------------------- 常数
        number,     // 数字
        //---------------------- 界符
        leftsym,    // (
        rightsym,   // )
        beginsym,   // {
        endsym,     // }
        comma,      // ,
        semicolon,  // ;
        period,     // .
        //---------------------- 标识符
        ident,      // 自定义符号
        //---------------------- 运算符
        plu,        // +
        sub,        // -
        mul,        // *
        dive,       // /
        eqlsym,     // =
        restasym,   // 赋值语句
    }

    /**
     * 判断字符类型
     */
    public enum wordType{
        BasicWord,  //基本字
        Number,     //常数
        Delimiter,  //界符
        Identifier, //标识符
        Operator    //运算符
    }

    private enum charType{
        Nul,        //空
        Letter,     //字母
        Number,     //数字类型
    }

    private charType getType(char ch){
        if(ch==' '||ch==10||ch==9){
            return charType.Nul;
        }else if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'||ch=='_'){
            return charType.Letter;
        }else if(ch>='0'&&ch<='9'){
            return charType.Number;
        }
        return null;
    }

    /**
     * 获取下个字符
     * @return 下一个字符，当返回'.'时表示文档结束
     */
    private char getChar(){
        while(sourceCodeTemp.equals("")){
            if(row>=Main.sourceCode.size()){
                return '.';
            }else{
                sourceCodeTemp = Main.sourceCode.get(row);
                row++;
                index = 0;
            }
        }
        char temp = sourceCodeTemp.charAt(index);
        index++;
        if(index>=sourceCodeTemp.length()){
            sourceCodeTemp = "";
        }
        return temp;
    }


    /**
     * 获取下一个单词
     */
    private void getSym(){

        char ch = getChar();
        while(true){
            if(ch=='.'){
                System.out.println("文档结束！");
                break;
            }else{
                if(getType(ch)!=charType.Nul){
                    //如果第一个单词是字母
                    if(getType(ch)==charType.Letter){
                        String stringTemp = "";
                        stringTemp+=ch;
                        ch = getChar();
                        while(getType(ch)==charType.Number||getType(ch)==charType.Letter){
                            stringTemp+=ch;
                            ch = getChar();
                        }
                        if(Arrays.asList(reservedWord).contains(stringTemp)){
                            System.out.println("保留字： "+stringTemp);
                        }else{
                            System.out.println("单词： "+stringTemp);
                        }
                    }else if(getType(ch)==charType.Number){
                        //如果第一个字母是数字
                        int numberTemp = ch - '0';
                        ch = getChar();
                        while(getType(ch)==charType.Number){
                            numberTemp = numberTemp*10+(ch-'0');
                            ch = getChar();
                        }
                        System.out.println("数字： "+numberTemp);
                    }else if(ch=='('){
                        System.out.println("符号： "+ch);
                        ch = getChar();
                    }else if(ch==')'){
                        System.out.println("符号： "+ch);
                        ch = getChar();
                    }else if(ch=='{'){
                        System.out.println("符号： "+ch);
                        ch = getChar();
                    }else if(ch=='}'){
                        System.out.println("符号： "+ch);
                        ch = getChar();
                    }else{
                        System.out.print(ch);
                        ch = getChar();
                    }
                }else{
                    ch = getChar();
                }
            }
        }
    }
}
