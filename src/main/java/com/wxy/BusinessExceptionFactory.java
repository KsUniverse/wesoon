package com.wxy;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */

public final class BusinessExceptionFactory {
    private BusinessExceptionFactory(){}
    public static BusinessException build(String code) {
        return new BusinessException(ErrorMessage.getErrorMessage(code));
    }
}
