package com.imudges.C0Compiler;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private String c0FileName;

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    public void outputInformation(){
        System.out.println("欢迎使用C0编译器");
        System.out.println("本编译器实现功能：");
    }

    public void init(){
        outputInformation();
        System.out.print("input C0 file? ");
        c0FileName = scanner.next();
        System.out.println("file Name is "+c0FileName);
    }
}
