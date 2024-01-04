package com.yahaha.controller;

import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.AdminUserInfoVo;
import com.yahaha.domain.VO.UserInfoVo;
import com.yahaha.domain.entity.LoginUser;
import com.yahaha.domain.entity.User;
import com.yahaha.enums.AppHttpCodeEnum;
import com.yahaha.exception.SystemException;
import com.yahaha.services.BlogLoginService;
import com.yahaha.services.MenuService;
import com.yahaha.services.RoleService;
import com.yahaha.utils.BeanCopyUtils;
import com.yahaha.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.adminLogin(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult getInfo() {
        // 获取登录信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUser().getId();
        // 根据用户id查询权限信息
        List<String> permsList = menuService.selectPermsByUserId(userId);
        // 根据用户id查询用户角色
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(userId);

        // 封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(
                permsList,
                roleKeyList,
                BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class)
        );
        return ResponseResult.okResult(adminUserInfoVo);
    }
}
