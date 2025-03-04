package com.zack.cloudpicturebackend.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @description: 用户登录VO
 * @createDate: 2025-02-17 21:50:24
 * @return: 脱敏后的用户
 */

@Data
public class LoginUserVO {

    private Long id;

    /**
     * 账号
     */
    private String userAccount;

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

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
