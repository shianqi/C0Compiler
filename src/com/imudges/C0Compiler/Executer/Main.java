package com.imudges.C0Compiler.Executer;

/**
 * C0 执行器
 * @author shianqi@imudges.com
 * Created by shianqi on 2016/11/1.
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    private void init(){
       /* Exe exe = new Exe();
        exe.init();*/
       Execute execute=new Execute();
       execute.init();
    }
}
