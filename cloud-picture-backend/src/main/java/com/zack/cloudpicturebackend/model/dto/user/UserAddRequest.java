package com.zack.cloudpicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserAddRequest implements Serializable {


    private static final long serialVersionUID = 1547560907135687282L;

    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;


}
