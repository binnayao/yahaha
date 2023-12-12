package com.yahaha.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {

    }

    public static <V> V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 实现属性copy

        // 返回结果
        return result;
    }

    public static <T, V> List<V> copyBeanList(List<T> list, Class<V> clazz) {
        return list.stream().map(v -> copyBean(v, clazz)).collect(Collectors.toList());

    }
}
