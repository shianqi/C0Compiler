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
        initFile();
        lex = new Lex();
    }

    /**
     * 初始化文件
     */
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

    /**
     * 读取文件
     * @param fileName 文件名称
     * @return 读取成功返回true，否则返回false
     */
    private boolean readC0File(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                sourceCode.add(tempString);
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
