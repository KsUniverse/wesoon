package com.wesoon.web.handler;

import com.alibaba.fastjson.JSONObject;
import com.wesoon.exception.BusinessException;
import com.wesoon.web.MvcContans;
import com.wesoon.web.RestResult;
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
    public String globalExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        RestResult restResult = new RestResult();
        restResult.setCode(MvcContans.STATUS_CODE_INTERNAL_ERROR);
        restResult.setSuccess(false);
        restResult.setData(null);
        if (e instanceof BusinessException) {
            restResult.setDesc(e.getMessage());
        } else {
            restResult.setDesc(MvcContans.DESC_ERROR);
        }
        return JSONObject.toJSONString(restResult);
    }
}
