package com.yahaha.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.RoutersVo;
import com.yahaha.domain.entity.LoginUser;
import com.yahaha.domain.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long UserId);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
