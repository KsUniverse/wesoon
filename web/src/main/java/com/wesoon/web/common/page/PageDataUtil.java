package com.wesoon.web.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wesoon.web.common.BeanCopyUtil;

import java.util.function.BiConsumer;

public class PageDataUtil {


    public static <S, T> PageResult<T> pageResult(Page<S> resource, Class<T> targetClass) {
        return pageResult(resource, targetClass, null);
    }


    public static <S, T> PageResult<T> pageResult(Page<S> resource, Class<T> targetClass,
                                                  BiConsumer<S, T> targetConsumer) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setTotal(resource.getTotal());
        pageResult.setPage(resource.getCurrent());
        pageResult.setSize(resource.getSize());
        pageResult.setData(BeanCopyUtil.copyList(resource.getRecords(), targetClass, targetConsumer));
        return pageResult;
    }

    public static <T extends PageParam> Page buildPage(T pageParam) {
        Page page = new Page();
        page.setCurrent(pageParam.getPage());
        page.setSize(pageParam.getSize());
        return page;
    }
}
