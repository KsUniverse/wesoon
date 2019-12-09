package com.wesoon.exception;

import com.wesoon.mvc.config.ErrorMessage;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */

public final class BusinessExceptionBuilder {

    private BusinessExceptionBuilder() {
    }

    public static BusinessException build(String code) {
        return new BusinessException(ErrorMessage.getErrorMessage(code));
    }
}
