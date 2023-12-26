package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.UserVo;
import com.yahaha.domain.entity.User;
import com.yahaha.enums.AppHttpCodeEnum;
import com.yahaha.exception.SystemException;
import com.yahaha.mapper.UserMapper;
import com.yahaha.services.UserService;
import com.yahaha.utils.BeanCopyUtils;
import com.yahaha.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public ResponseResult getUserInfo() {
        Long userId = SecurityUtils.getUserId();

        User user = getById(userId);

        UserVo userVo = BeanCopyUtils.copyBean(user, UserVo.class);

        return ResponseResult.okResult(userVo);
    }

    @Override
    public ResponseResult putUserInfo(User user) {
        Long userId = SecurityUtils.getUserId();
        user.setId(userId);
        boolean res = updateById(user);
        if (res) {
            return ResponseResult.okResult("保存成功");
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult registerUser(User user) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        // 对数据进行 是否存在的判断
        if (UserNameExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        // 密码加密
        String encodePwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);

        // 存入数据
        boolean res = save(user);

        if (res) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);

        }
    }

    private boolean UserNameExist(String userName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName, userName);
        int res = count(lambdaQueryWrapper);
        return res > 0;
    }
}
