package com.mindvoice.psych.common.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BeanListCopyUtils {

    /**
     * 将 sourceList 的对象拷贝为目标类型对象，返回新的 List
     *
     * @param sourceList  源对象 List
     * @param targetClass 目标对象 Class
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 拷贝后的新 List
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null) return null;
        return sourceList.stream().map(s -> {
            try {
                T target = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(s, target);
                return target;
            } catch (Exception e) {
                throw new RuntimeException("Bean copy error", e);
            }
        }).collect(Collectors.toList());
    }

    /**
     * 将 sourceList 的对象属性值拷贝到 targetList 对象（按索引对应）
     *
     * @param sourceList 源 List
     * @param targetList 目标 List
     * @param <S>        源类型
     * @param <T>        目标类型
     */
    public static <S, T> void copyListProperties(List<S> sourceList, List<T> targetList) {
        if (sourceList == null || targetList == null) return;
        int size = Math.min(sourceList.size(), targetList.size());
        for (int i = 0; i < size; i++) {
            BeanUtils.copyProperties(sourceList.get(i), targetList.get(i));
        }
    }

    /**
     * 使用 Supplier 创建目标对象，返回新的 List
     * 适合 targetClass 无法直接 newInstance 的情况
     *
     * @param sourceList 源对象 List
     * @param supplier   目标对象构造函数引用 (e.g. EmployeeDTO::new)
     * @param <S>        源类型
     * @param <T>        目标类型
     * @return 拷贝后的新 List
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Supplier<T> supplier) {
        if (sourceList == null) return null;
        return sourceList.stream().map(s -> {
            T target = supplier.get();
            BeanUtils.copyProperties(s, target);
            return target;
        }).collect(Collectors.toList());
    }
}
