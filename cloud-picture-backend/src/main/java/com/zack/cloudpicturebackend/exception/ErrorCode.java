package com.zack.cloudpicturebackend.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PARAMS_ERROR(40000,"请求参数异常"),
    NOT_LOGIN_ERROR(40100,"未登录"),
    NO_AUTH_ERROR(40101,"无权限"),
    NOT_FOUND_ERROR(40102,"请求数据不存在"),
    FRORBIDDEN_ERROR(40300,"禁止访问"),
    SYSTEM_ERROR(50000,"系统内部异常"),
    OPERATION_ERROR(50001,"操作失败");

    private final Integer code;
    private final String msg;

    ErrorCode(Integer code,String  msg){
        this.code=code;
        this.msg=msg;
    }
}
