package com.yahaha.services.impl;

import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.BlogUserLoginBaseVo;
import com.yahaha.domain.VO.BlogUserLoginVo;
import com.yahaha.domain.VO.UserInfoVo;
import com.yahaha.domain.entity.LoginUser;
import com.yahaha.domain.entity.User;
import com.yahaha.services.BlogLoginService;
import com.yahaha.utils.BeanCopyUtils;
import com.yahaha.utils.JwtUtil;
import com.yahaha.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    public LoginUser getLoginUser(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // 会调用 UserDetails 所以项目中需要重写 UserDetails
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取userId 生成 token
        return (LoginUser) authenticate.getPrincipal();
    }

    public String getJwtToken(LoginUser loginUser) {
        String userId = loginUser.getUser().getId().toString();
        return JwtUtil.createJWT(userId);
    }

    @Override
    public ResponseResult login(User user) {
        LoginUser loginUser = getLoginUser(user.getUserName(), user.getPassword());
        String jwt = getJwtToken(loginUser);

        // 把用户信息存入redis
        redisCache.setCacheObject(
                SystemConstants.REDIS_KEY_LOGIN_USER_PREFIX + loginUser.getUser().getId().toString(),
                loginUser
        );
        // 把token和userInfo封装 返回
        // User转换成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult adminLogin(User user) {
        LoginUser loginUser = getLoginUser(user.getUserName(), user.getPassword());
        String jwt = getJwtToken(loginUser);

        redisCache.setCacheObject(
                SystemConstants.REDIS_KEY_ADMIN_LOGIN_USER_PREFIX + loginUser.getUser().getId().toString(),
                loginUser
        );

        return ResponseResult.okResult(new BlogUserLoginBaseVo(jwt));
    }

    @Override
    public ResponseResult getInfo() {
        return null;
    }

    @Override
    public ResponseResult logout() {
        // 获取token 解析获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取userId
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject(SystemConstants.REDIS_KEY_LOGIN_USER_PREFIX + userId);
        return ResponseResult.okResult();
    }


}
