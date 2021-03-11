package com.wesoon.web.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Slf4j
public final class Check {

    public static void isNotNull(Object o, String code) {
        if (o == null) {
            throw new BusinessException(code);
        }
    }

    public static void isNotEmpty(Object o, String code) {
        isNotNull(o, code);
        if (o instanceof String && ((String) o).trim().length() > 0) {
            return;
        } else if (o instanceof Iterator && ((Iterator) o).hasNext()) {
            return;
        }
        log.warn("check unknown data type");
        throw new BusinessException(code);
    }
}

