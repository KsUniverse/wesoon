package com.wesoon.mvc.handler;

import com.wesoon.exception.BusinessException;
import com.wesoon.mvc.MvcHttpConstant;
import com.wesoon.mvc.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResult globalExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        RestResult restResult = new RestResult();
        restResult.setCode(MvcHttpConstant.STATUS_CODE_INTERNAL_ERROR);
        restResult.setSuccess(false);
        restResult.setData(null);
        if (e instanceof BusinessException) {
            restResult.setDesc(e.getMessage());
        } else {
            restResult.setDesc(MvcHttpConstant.DESC_ERROR);
        }
        return restResult;
    }
}
