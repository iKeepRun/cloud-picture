package com.zack.cloudpicturebackend.aop;


import cn.hutool.core.util.StrUtil;
import com.zack.cloudpicturebackend.annotation.AuthCheck;
import com.zack.cloudpicturebackend.constant.UserConstant;
import com.zack.cloudpicturebackend.exception.BusinessException;
import com.zack.cloudpicturebackend.exception.ErrorCode;
import com.zack.cloudpicturebackend.model.entity.User;
import com.zack.cloudpicturebackend.model.enums.UserRoleEnum;
import com.zack.cloudpicturebackend.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthInteceptor {
    @Resource
    UserService userService;

    @Around("@annotation(authCheck)")
    public Object authCheck(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {

        String mustRole = authCheck.mustRole();
        if(StrUtil.isBlank(mustRole)){
            return  joinPoint.proceed();
        }

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);

        //获取当前登录的用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if(loginUser == null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        //需要管理员权限，但是用户没有该权限
        if(UserRoleEnum.ADMIN.equals(mustRoleEnum)&&!(UserRoleEnum.ADMIN.equals(userRoleEnum))){
            throw  new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return joinPoint.proceed();
    }
}
