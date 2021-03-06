package com.wesoon.web.mvc.config;

import com.wesoon.web.exception.BusinessException;
import com.wesoon.web.mvc.MvcHttpConstant;
import com.wesoon.web.mvc.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public RestResult BusinessExceptionHandler(HttpServletRequest request, BusinessException e) {
        log.error(e.getMessage(), e);
        RestResult restResult = new RestResult();
        restResult.setCode(MvcHttpConstant.STATUS_CODE_BUSINESS_ERROR);
        restResult.setSuccess(false);
        restResult.setData(null);
        restResult.setDesc(e.getMessage());
        return restResult;
    }

    @ExceptionHandler(Exception.class)
    public RestResult globalExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        RestResult restResult = new RestResult();
        restResult.setCode(MvcHttpConstant.STATUS_CODE_INTERNAL_ERROR);
        restResult.setSuccess(false);
        restResult.setData(null);
        restResult.setDesc(MvcHttpConstant.INTERNAL_DESC_ERROR);
        return restResult;
    }
}
