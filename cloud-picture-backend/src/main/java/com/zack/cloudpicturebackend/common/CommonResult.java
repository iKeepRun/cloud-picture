package com.zack.cloudpicturebackend.common;

import com.zack.cloudpicturebackend.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;


    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult(0,"success",data);
    }

    public static <T> CommonResult<T> error(ErrorCode errorCode){
        return new CommonResult(errorCode.getCode(),errorCode.getMsg(),null);
    }

    public static <T> CommonResult<T> error(int code,String message){
        return new CommonResult(code,message,null);
    }
}
