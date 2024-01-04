package com.yahaha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yahaha.domain.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(@Param("id") Long id);
}
