package com.zack.cloudpicturebackend.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
   private  String message;
   private  Integer code;

   public BusinessException(String message, Integer code) {
      this.message = message;
      this.code = code;
   }

   public BusinessException(ErrorCode errorCode) {
      this.message = errorCode.getMsg();
      this.code=errorCode.getCode();
   }

   public BusinessException(ErrorCode errorCode,String message) {
      this.message = message;
      this.code=errorCode.getCode();
   }
}
