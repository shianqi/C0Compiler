package com.imudges.C0Compiler.Compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by killer on 2016/10/18.
 * @author shianqi@imudges.com
 * c0编译器词法分析类
 */
public class Lex {
    private String sourceCodeTemp;
    private int row;
    private int index;

    public Lex(){
        sourceCodeTemp = "";
        row = 0;
        index = 0;
        getSym();
    }

    public enum symbol
    {
        //---------------------- 基本字
        mainsym,    // 主程序
        voidsym,    // 空类型
        ifstasym,   // 如果语句
        whilestasym,// 循环语句
        resym,      // return
        varsym,     // 变量定义
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

    public enum charType{
        Nul,        //空
        Letter,     //字母
        Delimiter,

    }

    private charType getType(char ch){
        if(ch==' '||ch==10||ch==9){
            return charType.Nul;
        }else if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'||ch=='_'){
            return charType.Letter;
        }else if(ch=='('||ch==')'||ch=='{'||ch=='}'){
            return charType.Delimiter;
        }
        //
        return null;
    }

    private char getChar(){
        if(sourceCodeTemp.equals("")){
            if(row+1>=Main.sourceCode.size()){
                return ' ';
            }else{
                sourceCodeTemp = Main.sourceCode.get(row);
            }
        }
        //
        return ' ';
    }

    private void getSym(){
        while(true){
            char ch = getChar();
            if(ch==' '){
                System.out.println("文档结束！");
                break;
            }else{
                System.out.println(ch);
            }
        }
    }
}
