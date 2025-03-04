package com.zack.cloudpicturebackend.exception;

import com.zack.cloudpicturebackend.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public CommonResult<?> businessExceptionHandler(BusinessException e){
        log.error("BusinessException",e);
        return CommonResult.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResult<?> runTimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException",e);
        return CommonResult.error(ErrorCode.SYSTEM_ERROR);
    }


}
