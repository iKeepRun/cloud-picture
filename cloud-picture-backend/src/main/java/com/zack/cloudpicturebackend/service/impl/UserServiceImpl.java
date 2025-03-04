package com.zack.cloudpicturebackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zack.cloudpicturebackend.constant.UserConstant;
import com.zack.cloudpicturebackend.exception.BusinessException;
import com.zack.cloudpicturebackend.exception.ErrorCode;
import com.zack.cloudpicturebackend.model.dto.user.UserQueryRequest;
import com.zack.cloudpicturebackend.model.entity.User;
import com.zack.cloudpicturebackend.model.enums.UserRoleEnum;
import com.zack.cloudpicturebackend.model.vo.LoginUserVO;
import com.zack.cloudpicturebackend.model.vo.UserVO;
import com.zack.cloudpicturebackend.service.UserService;
import com.zack.cloudpicturebackend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* @author chenzhiqiang
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-02-17 21:50:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public long userRegister(String userAccount, String password, String checkPassword) {
        //校验参数
        if(StrUtil.hasBlank(userAccount,password,checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数异常");
        }
        if(!password.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次密码不一致");
        }
        //校验用户是否存在
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("userAccount",userAccount);


        Long count = this.baseMapper.selectCount(wrapper);
        if(count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号重复");
        }
        //密码加密
        User user = new User();

        user.setUserAccount(userAccount);
        user.setUserPassword(md5PswEncrypte((password)));
        user.setUserName("无名");
        user.setUserRole(UserRoleEnum.USER.getValue());

        //插入数据库
        boolean saveresult = this.save(user);
        if(!saveresult){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"注册失败，数据库错误");
        }
        return user.getId();
    }

    public String md5PswEncrypte(String password){
        final String SALT="zack";
        return DigestUtils.md5DigestAsHex((SALT+password).getBytes());
    }


    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //校验参数
        if(StrUtil.hasBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数异常");
        }
        //校验用户是否存在
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("userAccount",userAccount);
        wrapper.eq("userPassword",md5PswEncrypte(userPassword));
        User user = this.baseMapper.selectOne(wrapper);
        if(user==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号或密码错误");
        }
        //设置session
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATUS,user);
        //返回脱敏用户信息
        LoginUserVO loginUserVO = BeanUtil.copyProperties(user, LoginUserVO.class);
        return loginUserVO;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        User user  = (User)request.getSession().getAttribute(UserConstant.USER_LOGIN_STATUS);
        return user;
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATUS);
        if(attribute==null){
           throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户未登录");
        }
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATUS);
        return true;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user){
        return  BeanUtil.copyProperties(user, LoginUserVO.class);
    }


    @Override
    public UserVO getUserVO(User user){
        return  BeanUtil.copyProperties(user, UserVO.class);
    }

    @Override
    public List<UserVO> getUserVOList(List<User> users){
        if(CollUtil.isEmpty(users)){
            return new ArrayList<>();
        }
        return   BeanUtil.copyToList(users, UserVO.class);
    }


    public QueryWrapper<User> getWrapper(UserQueryRequest userQueryRequest){

        if(userQueryRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(userQueryRequest.getId()!=null){
            wrapper.eq("id",userQueryRequest.getId());
        }
        if(StrUtil.isNotBlank(userQueryRequest.getUserAccount())){
            wrapper.like("userAccount",userQueryRequest.getUserAccount());
        }
        if(StrUtil.isNotBlank(userQueryRequest.getUserName())){
            wrapper.like("userName",userQueryRequest.getUserName());
        }

        if(StrUtil.isNotBlank(userQueryRequest.getUserRole())){
            wrapper.eq("userRole",userQueryRequest.getUserRole());
        }
        if (userQueryRequest.getUserProfile()!=null){
            wrapper.like("userProfile",userQueryRequest.getUserProfile());
        }
        return wrapper;
    }

    @Override
    public String getEncryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}




