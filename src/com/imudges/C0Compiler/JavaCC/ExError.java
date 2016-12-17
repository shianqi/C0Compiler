package com.imudges.C0Compiler.JavaCC;
/**
 * @author shianqi@imudges.com
 * Created by shianqi on 2016/12/14.
 */
public class ExError {
    public static enum ErrCode{
        ArithmeticException,    //算数异常
        InputParamErrException, //输入参数错误
        VariableException,      //变量异常
        UnknowException         //未知错误
    }
    public static boolean errFlag=false;
    public static void ShowErrMsg(int errCode,String errMsg){
        switch (errCode){
            case 1:{showMessage(ErrCode.ArithmeticException,errMsg);break;}
            case 2:{showMessage(ErrCode.InputParamErrException,errMsg);break;}
            case 3:{showMessage(ErrCode.VariableException,errMsg);break;}
            default:{showMessage(ErrCode.VariableException,errMsg);break;}
        }

    }

    private static void showMessage(ErrCode errCode,String errMsg){
        System.err.println("Error-"+errCode.toString()+": "+errMsg);
        errFlag=true;
    }

    /*public static void main(String[] args) {
        System.out.println(ErrCode.askMemoryTooBigException.toString());
    }*/
}
