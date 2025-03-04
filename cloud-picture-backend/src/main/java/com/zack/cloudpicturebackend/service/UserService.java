package com.zack.cloudpicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zack.cloudpicturebackend.model.dto.user.UserQueryRequest;
import com.zack.cloudpicturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zack.cloudpicturebackend.model.vo.LoginUserVO;
import com.zack.cloudpicturebackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author chenzhiqiang
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-02-17 21:50:24
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount,String password,String checkPassword);

    String md5PswEncrypte(String password);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);

    LoginUserVO getLoginUserVO(User user);

    UserVO getUserVO(User user);

    List<UserVO> getUserVOList(List<User> users);

    QueryWrapper<User> getWrapper(UserQueryRequest userQueryRequest);

    String getEncryptPassword(String password);
}
