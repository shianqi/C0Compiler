package com.imudges.C0Compiler.Compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shianqi on 2016/10/11.
 * @author shianqi@imudges.com
 */
public class Main {
    private Lex lex;
    private Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> sourceCode;

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    /**
     * 简单的程序介绍
     */
    private void outputInformation(){
        System.out.println("欢迎使用C0编译器");
        System.out.println("本编译器实现功能：");
    }

    /**
     * 初始化程序
     *
     */
    private void init(){
        outputInformation();
        sourceCode = new ArrayList<>();
        lex = new Lex();

        initFile();
        lex.run();
    }

    private void initFile(){
        while (true){
            System.out.println("input the c0 file name");
            if(readC0File(scanner.next())){
                break;
            }else{
                System.out.println("c0 File read failed. Please try again!");
            }
        }
    }

    private boolean readC0File(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println(line+": "+tempString);
                line++;
            }
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    System.out.println("document close failed!");
                }
            }
        }
    }
}
