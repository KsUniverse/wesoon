package com.wesoon.web.mvc.config;

import com.wesoon.web.mvc.MvcHttpConstant;
import com.wesoon.web.mvc.RestResult;
import com.wesoon.web.mvc.annotation.other.OnRestReturn;
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
public class RestReturnValueHandler implements ResponseBodyAdvice<Object> {

    private String controllerBasePackages;

    public RestReturnValueHandler(String controllerBasePackages) {
        this.controllerBasePackages = controllerBasePackages;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if(methodParameter.getMethod().isAnnotationPresent(OnRestReturn.class)) {
            return true;
        }
        if(methodParameter.getDeclaringClass().getName().startsWith(controllerBasePackages)) {
            return methodParameter.getDeclaringClass().isAnnotationPresent(RestController.class)
                    || methodParameter.getMethod().isAnnotationPresent(ResponseBody.class);
        }
        return false;
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


