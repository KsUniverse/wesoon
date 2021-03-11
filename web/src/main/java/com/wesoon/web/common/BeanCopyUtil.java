package com.wesoon.web.common;

import com.wesoon.web.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;


/**
 * @Description 处理bean 复制
 * @Author wangpeng
 * @Date 2020/4/22 14:49
 */
@Slf4j
public class BeanCopyUtil {

    /**
     * list copy
     *
     * @param sourceList  来源list
     * @param targetClass 目标 list 中类 class
     */
    public static <T, S> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sourceList)) {
            return targetList;
        }
        for (S source : sourceList) {
            try {
                T target = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source, target);
                targetList.add(target);
            } catch (Exception e) {
                log.error("bean copy exception", e);
            }
        }
        return targetList;
    }

    /**
     * list copy
     *
     * @param sourceList     来源list
     * @param targetClass    目标 list 中类 class
     * @param targetConsumer 目标类处理
     */
    public static <T, S> List<T> copyList(List<S> sourceList, Class<T> targetClass, BiConsumer<S, T> targetConsumer) {
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sourceList)) {
            return targetList;
        }
        for (S source : sourceList) {
            try {
                T target = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source, target);
                if (targetConsumer != null) {
                    targetConsumer.accept(source, target);
                }
                targetList.add(target);
            } catch (Exception e) {
                log.error("bean copy exception", e);
            }
        }
        return targetList;
    }

    /**
     * 将源对象拷贝生成一个新类型的对象
     *
     * @param resource    源对象
     * @param targetClass 新对象class
     * @throws BusinessException 源对象为null 抛"未查询到数据"异常
     */
    public static <S, T> T copyObject(S resource, Class<T> targetClass) {
        try {
            return copyObject(resource,  targetClass.getDeclaredConstructor().newInstance(), new BusinessException("未查询到数据"),  null);
        } catch (Exception e) {
            throw new RuntimeException("bean copy exception", e);
        }
    }

    /**
     * 将源对象拷贝生成一个新类型的对象
     *
     * @param resource    源对象
     * @param targetClass 新对象class
     * @throws BusinessException 源对象为null 抛"未查询到数据"异常
     */
    public static <S, T> T copyObject(S resource, Class<T> targetClass, BiConsumer<S, T> targetConsumer) {
        try {
            return copyObject(resource,  targetClass.getDeclaredConstructor().newInstance(), new BusinessException("未查询到数据"),  targetConsumer);
        } catch (Exception e) {
            throw new RuntimeException("bean copy exception", e);
        }
    }

    /**
     * 将源对象拷贝生成一个新类型的对象, 如果源为null 返回null
     *
     * @param resource    源对象
     * @param targetClass 新对象class
     */
    public static <S, T> T copyObjectOptional(S resource, Class<T> targetClass) {
        try {
            return copyObject(resource, targetClass.getDeclaredConstructor().newInstance(), null, null);
        } catch (Exception e) {
            throw new RuntimeException("bean copy exception", e);
        }
    }


    /**
     * 将源对象拷贝生成一个新类型的对象, 如果源为null 返回null
     *
     * @param resource    源对象
     * @param targetClass 新对象class
     */
    public static <S, T> T copyObjectOptional(S resource, Class<T> targetClass, BiConsumer<S, T> targetConsumer) {
        try {
            return copyObject(resource, targetClass.getDeclaredConstructor().newInstance(), null, targetConsumer);
        } catch (Exception e) {
            throw new RuntimeException("bean copy exception", e);
        }
    }

    /**
     * 将源对象拷贝生成一个新类型的对象
     *
     * @param resource      源对象
     * @param target  新对象
     * @param failException 当源对象为空时, 抛出指定的异常
     * @throws RuntimeException 源对象为null 抛异常
     */
    public static <T, S> T copyObject(S resource, T target, RuntimeException failException, BiConsumer<S, T> targetConsumer) {
        if (resource == null) {
            log.info("bean copy exception, 源数据为null, 目标对象类型: [{}]", target.getClass().getName());
            throw failException;
        }
        BeanUtils.copyProperties(resource, target);
        if (targetConsumer != null) {
            targetConsumer.accept(resource, target);
        }
        return target;
    }

    /**
     * 将源对象数据复制到目标对象中
     *
     * @param resource 源对象
     * @param target   目标对象
     * @throws BusinessException 源对象为null, 抛"未查询到数据"异常
     */
    public static <S, T> void copyObject(S resource, T target) {
        copyObject(resource, target, new BusinessException("未查询到数据"), null);
    }

    /**
     * 将源对象数据复制到目标对象中
     *
     * @param resource 源对象
     * @param target   目标对象
     * @throws BusinessException 源对象为null, 抛"未查询到数据"异常
     */
    public static <S, T> void copyObject(S resource, T target, BiConsumer<S, T> targetConsumer) {
        copyObject(resource, target, new BusinessException("未查询到数据"), targetConsumer);
    }
}
