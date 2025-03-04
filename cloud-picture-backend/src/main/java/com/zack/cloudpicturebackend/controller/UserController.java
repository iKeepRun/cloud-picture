package com.zack.cloudpicturebackend.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zack.cloudpicturebackend.annotation.AuthCheck;
import com.zack.cloudpicturebackend.common.CommonResult;
import com.zack.cloudpicturebackend.common.DeleteRequest;
import com.zack.cloudpicturebackend.constant.UserConstant;
import com.zack.cloudpicturebackend.exception.BusinessException;
import com.zack.cloudpicturebackend.exception.ErrorCode;
import com.zack.cloudpicturebackend.exception.ThrowUtil;
import com.zack.cloudpicturebackend.model.dto.user.*;
import com.zack.cloudpicturebackend.model.entity.User;
import com.zack.cloudpicturebackend.model.vo.LoginUserVO;
import com.zack.cloudpicturebackend.model.vo.UserVO;
import com.zack.cloudpicturebackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/register")
    public CommonResult<Long> registerUser(@RequestBody UserRegisterRequest userRegisterRequest){
        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, password, checkPassword);
        return CommonResult.success(result);
    }

    @PostMapping("/login")
    public CommonResult<LoginUserVO> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        ThrowUtil.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getUserPassword();
        LoginUserVO result = userService.userLogin(userAccount, password,request);

        return CommonResult.success(result);
    }



    @GetMapping("/getLoginUser")
    public CommonResult<LoginUserVO> getLoginUser(HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        String ds="dadad";
        return CommonResult.success(userService.getLoginUserVO(loginUser));
    }

    @GetMapping("/logout")
    public CommonResult<Boolean> logout(HttpServletRequest request){
        return CommonResult.success(userService.userLogout(request));
    }

    /**
     * 新增用户信息
     * @param userAddRequest
     * @return
     */
    @PostMapping("/addUser")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addUser(@RequestBody UserAddRequest userAddRequest){
        ThrowUtil.throwIf(userAddRequest == null, ErrorCode.PARAMS_ERROR);
        User user = new User();
        BeanUtil.copyProperties(userAddRequest, user);
        final String DEFAULT_PASSWORD = "123456";
        user.setUserPassword(userService.getEncryptPassword(DEFAULT_PASSWORD));
        boolean result = userService.save(user);
        ThrowUtil.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return CommonResult.success(user.getId());
    }

    /**
     * 根据id获取用户信息  仅限管理员
     * @param id
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public CommonResult<User> getUserById(long id){
        ThrowUtil.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtil.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return CommonResult.success(user);
    }


    /**
     * 根据id获取封装用户
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public CommonResult<UserVO> getUserVOById(long id){
        ThrowUtil.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User data = this.getUserById(id).getData();
        UserVO userVO = userService.getUserVO(data);
        return CommonResult.success(userVO);
    }

    /**
     * 根据id删除用户
     * @param deleteRequest
     * @return
     */
    @DeleteMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> deleteUserById(@RequestBody DeleteRequest deleteRequest){
        if (deleteRequest == null||deleteRequest.getId()<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result=userService.removeById(deleteRequest.getId());
        return CommonResult.success(result);
    }

    /**
     * 更新用户信息
     * @param userUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        if (userUpdateRequest == null||userUpdateRequest.getId()==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = BeanUtil.copyProperties(userUpdateRequest, User.class);
        boolean result=userService.updateById(user);
        return CommonResult.success(result);
    }

    /**分页获取封装用户列表
     * @param userQueryRequest
     */
    @PostMapping("/list/page/vo")
    public CommonResult<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest){
        ThrowUtil.throwIf(userQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Integer current = userQueryRequest.getCurrent();
        Integer pageSize = userQueryRequest.getPageSize();

        Page<User> page = userService.page(new Page<>(current, pageSize), userService.getWrapper(userQueryRequest));

        List<UserVO> userVOList = userService.getUserVOList(page.getRecords());
        Page<UserVO> voPage = new Page<>(current,pageSize,page.getTotal());
        voPage.setRecords(userVOList);
        return CommonResult.success(voPage);
    }
}
