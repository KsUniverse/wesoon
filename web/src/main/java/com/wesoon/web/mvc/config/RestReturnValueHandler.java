package com.wesoon.web.mvc.config;

import com.alibaba.fastjson.JSONObject;
import com.wesoon.web.mvc.MvcHttpConstant;
import com.wesoon.web.mvc.RestResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/10/14
 * @Version 1.0
 */

@Component
public class RestReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.hasMethodAnnotation(ResponseBody.class)
                || returnType.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        RestResult restResult = new RestResult();
        restResult.setCode(MvcHttpConstant.STATUS_CODE_SUCCEEDED);
        restResult.setSuccess(true);
        restResult.setData(returnValue);
        restResult.setDesc(MvcHttpConstant.DESC_SUCCESS);
        response.getWriter().write(JSONObject.toJSONString(restResult));
    }

}


