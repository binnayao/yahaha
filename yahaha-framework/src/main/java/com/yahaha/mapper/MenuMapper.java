package com.yahaha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yahaha.domain.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouteMenu(Long userId);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
