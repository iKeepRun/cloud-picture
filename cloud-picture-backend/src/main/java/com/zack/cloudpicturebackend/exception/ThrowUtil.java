package com.zack.cloudpicturebackend.exception;

public class ThrowUtil {

    public static void throwIf(boolean condition,RuntimeException runtimeException){
        if(condition) throw runtimeException;
    }
    public static void throwIf(boolean condition,ErrorCode errorCode){
        throwIf(condition,new BusinessException(errorCode));
    }

    // public static void throwIfNot(boolean condition,String  msg){
    //     throwIf(!condition,errorCode);
    // }
}
