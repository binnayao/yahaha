package com.yahaha.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.UserVo;
import com.yahaha.domain.entity.User;
import com.yahaha.enums.AppHttpCodeEnum;
import com.yahaha.mapper.UserMapper;
import com.yahaha.services.UserService;
import com.yahaha.utils.BeanCopyUtils;
import com.yahaha.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
