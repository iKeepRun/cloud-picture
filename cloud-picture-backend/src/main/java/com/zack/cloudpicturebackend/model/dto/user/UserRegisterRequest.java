package com.zack.cloudpicturebackend.model.dto.user;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -7528289133029780941L;
    private String userAccount;
    private String password;
    private String checkPassword;



}
