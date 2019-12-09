package com.wesoon.mvc.config;

import com.wesoon.mvc.handler.RestReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Configuration
public class InitializationConfig {

    @Autowired
    private RequestMappingHandlerAdapter adapter;
    @Autowired
    private RestReturnValueHandler restReturnValueHandler;


    @PostConstruct
    public void initTokenModelAttributeMethodProcessor() {
        List<HandlerMethodReturnValueHandler> argumentResolvers = new ArrayList<>(adapter.getReturnValueHandlers());
        List<HandlerMethodReturnValueHandler> customResolvers = adapter.getCustomReturnValueHandlers();
        // 调整自定义参数解析器优先级
        if (customResolvers != null) {
            argumentResolvers.removeAll(customResolvers);
            argumentResolvers.add(0, restReturnValueHandler);
        }
        // 重新设置参数解析优先级
        adapter.setReturnValueHandlers(argumentResolvers);
    }
}
