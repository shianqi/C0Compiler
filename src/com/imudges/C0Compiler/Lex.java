package com.imudges.C0Compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

/**
 * Created by killer on 2016/10/18.
 * @author shianqi@imudges.com
 * c0编译器词法分析类
 */
public class Lex {
    private Scanner scanner = new Scanner(System.in);
    

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

    private char getChar(){
        return ' ';
    }

    /**
     * 文件读写模块
     */
    public void readC0File(){
        String c0FileName = "";
        System.out.print("input C0 file? ");
        c0FileName = scanner.next();
        System.out.println("file Name is "+c0FileName);
        File file = new File(c0FileName);
        Reader reader = null;
        try{
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: 系统找不到指定文件!");
        }
    }
}
