package com.yahaha.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yahaha.constants.SystemConstants;
import com.yahaha.domain.ResponseResult;
import com.yahaha.domain.VO.RoutersVo;
import com.yahaha.domain.entity.Menu;
import com.yahaha.mapper.MenuMapper;
import com.yahaha.services.MenuService;
import com.yahaha.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> selectPermsByUserId(Long id) {
        // 如果是管理员, 返回所有的权限
        if (SecurityUtils.isAdmin()) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU_TYPE_MENU_ITEM, SystemConstants.MENU_TYPE_BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
            List<Menu> mens = list(wrapper);
            List<String> perms = mens.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        // 否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        List<Menu> menus;
        if (SecurityUtils.isAdmin()) {
            menus = menuMapper.selectAllRouteMenu(userId);
        } else {
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        // 构建tree
        return buildMenuTree(menus);
    }

    private List<Menu> buildMenuTree(List<Menu> menus) {
        List<Menu> parentList = menus.stream().filter(
                menu -> menu.getParentId() == 0
        ).toList();
        parentList.forEach(parent -> {
            List<Menu> children = menus.stream().filter(
                    menu -> menu.getParentId().equals(parent.getId())
            ).toList();
            parent.setChildren(children);
        });
        return parentList;
    }
}
