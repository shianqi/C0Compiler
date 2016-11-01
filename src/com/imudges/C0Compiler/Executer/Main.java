package com.imudges.C0Compiler.Executer;

import java.util.Scanner;
import java.util.Stack;

/**
 * C0 执行器
 * Created by killer on 2016/11/1.
 */
public class Main {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
//        main.test();
    }

    public void test(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.set(stack.size()-1,3);
        System.out.print(stack.peek());
    }

    public void init(){
        String fileName = scanner.next();
        Exe exe = new Exe();
        exe.readFileByLines(fileName);
    }
}
