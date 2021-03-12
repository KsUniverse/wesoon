package com.wesoon.web.mvc.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
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

    private RequestMappingHandlerAdapter adapter;
    private RestReturnValueHandler restReturnValueHandler;

    public InitializationConfig(RequestMappingHandlerAdapter adapter, RestReturnValueHandler restReturnValueHandler) {
        this.adapter = adapter;
        this.restReturnValueHandler = restReturnValueHandler;
    }

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

    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
