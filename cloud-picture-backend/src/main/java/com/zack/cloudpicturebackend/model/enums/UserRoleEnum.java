package com.zack.cloudpicturebackend.model.enums;


import cn.hutool.core.util.StrUtil;
import lombok.Getter;

@Getter
public enum UserRoleEnum {

    ADMIN("管理员","admin"),
    USER("用户","user");


    private final String name;
    private final String value;


    UserRoleEnum(String name,String value){
        this.name=name;
        this.value=value;
    }


   public static UserRoleEnum  getEnumByValue(String value ){
       if(StrUtil.isBlank(value)){
           return null;
       }
       for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
           if(userRoleEnum.getValue().equals(value)){
               return userRoleEnum;
           }
       }

       return null;
   }
}
