package com.hworld.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * dto与do转换工具
 *
 * @param <Dto>
 * @param <Do>
 */
public class MyBeanUtils<Dto, Do> {

    /**
     * dot 转换为Do 工具类
     */
    public static <Do> Do dtoToDo(Object dtoEntity, Class<Do> doClass) {
        // 判断dto是否为空!
        if (dtoEntity == null) {
            return null;
        }
        // 判断DoClass 是否为空
        if (doClass == null) {
            return null;
        }
        try {
            Do newInstance = doClass.newInstance();
            BeanUtils.copyProperties(dtoEntity, newInstance);
            // Dto转换Do
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * do 转换为Dto 工具类
     */
    public static <Dto> Dto doToDto(Object doEntity, Class<Dto> dtoClass) {
        // 判断dto是否为空!
        if (doEntity == null) {
            return null;
        }
        // 判断DoClass 是否为空
        if (dtoClass == null) {
            return null;
        }
        try {
            Dto newInstance = dtoClass.newInstance();
            BeanUtils.copyProperties(doEntity, newInstance);
            // Dto转换Do
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * vo 转换为vo 工具类
     */
    public static <Do> Do voTodo(Object voEntity, Class<Do> respVoClass) {
        // 判断dto是否为空!
        if (voEntity == null) {
            return null;
        }
        // 判断DoClass 是否为空
        if (respVoClass == null) {
            return null;
        }
        try {
            Do newInstance = respVoClass.newInstance();
            BeanUtils.copyProperties(voEntity, newInstance);
            // Dto转换Do
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * vo 转换为vo 工具类
     */
    public static <Vo> Vo doToVo(Object doEntity, Class<Vo> respVoClass) {
        // 判断dto是否为空!
        if (doEntity == null) {
            return null;
        }
        // 判断DoClass 是否为空
        if (respVoClass == null) {
            return null;
        }
        try {
            Vo newInstance = respVoClass.newInstance();
            BeanUtils.copyProperties(doEntity, newInstance);
            // Dto转换Do
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    public static void copyProperties(Object source, Object target) {
        if (source == null) {
            return;
        }
        if (target == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
            // Dto转换Do
            return;
        } catch (Exception e) {
            return;
        }
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        if (source == null) {
            return;
        }
        if (target == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(source, target, ignoreProperties);
            // Dto转换Do
            return;
        } catch (Exception e) {
            return;
        }
    }


    /**
     * 获取Object 为空的字段
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}