package com.imudges.C0Compiler.Compiler;

/**
 * Created by shianqi on 2016/10/11.
 * @author shianqi@imudges.com
 */
public class Main {
    private Lex lex;

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
        lex = new Lex();
        lex.run();
    }
}
