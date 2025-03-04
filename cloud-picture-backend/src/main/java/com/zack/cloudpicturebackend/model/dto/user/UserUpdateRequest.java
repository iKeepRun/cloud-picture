package com.zack.cloudpicturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1547560907135687282L;


    private Long id;

    private String userAccount;

    private String userName;

    private String userAvatar;

    private String userProfile;

    private String userRole;
}
