package com.zack.cloudpicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -6634191988293742488L;
    private String userAccount;
    private String userPassword;
}
