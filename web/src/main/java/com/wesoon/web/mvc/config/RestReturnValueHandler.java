package com.wesoon.web.mvc.config;

import com.wesoon.web.mvc.MvcHttpConstant;
import com.wesoon.web.mvc.RestResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/10/14
 * @Version 1.0
 */

@ControllerAdvice
public class RestReturnValueHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getMethod().isAnnotationPresent(ResponseBody.class)
                || methodParameter.getDeclaringClass().isAnnotationPresent(RestController.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        RestResult restResult = new RestResult();
        restResult.setCode(MvcHttpConstant.STATUS_CODE_SUCCEEDED);
        restResult.setSuccess(true);
        restResult.setData(body);
        restResult.setDesc(MvcHttpConstant.DESC_SUCCESS);
        return restResult;
    }
}


