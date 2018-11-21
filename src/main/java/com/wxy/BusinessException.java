package com.wxy;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
