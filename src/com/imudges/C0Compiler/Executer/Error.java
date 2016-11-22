package com.imudges.C0Compiler.Executer;

/**
 * 错误处理类
 * @author shianqi@imudges.com
 * Created by shianqi on 2016/11/1.
 */
public class Error {
    public static enum ErrCode{
        ArithmeticException,//算数异常
        InputParamErrException,//输入参数错误
        askMemoryTooBigException,//申请内存空间太大
        runStackEmptyException,//栈空
    }
    public static boolean errFlag=false;
    public static void ShowErrMsg(int errCode,String errMsg){
        switch (errCode){
            case 1:{System.err.println("错误信息:"+ErrCode.ArithmeticException.toString()+" "+errMsg);errFlag=true;break;}
            case 2:{System.err.println("错误信息:"+ErrCode.InputParamErrException.toString()+" "+errMsg);errFlag=true;break;}
            case 3:{System.err.println("错误信息:"+ErrCode.askMemoryTooBigException.toString()+" "+errMsg);errFlag=true;break;}
            case 4:{System.err.println("错误信息:"+ErrCode.runStackEmptyException.toString()+" "+errMsg);errFlag=true;break;}
            default:{}
        }

    }

    /*public static void main(String[] args) {
        System.out.println(ErrCode.askMemoryTooBigException.toString());
    }*/
}
