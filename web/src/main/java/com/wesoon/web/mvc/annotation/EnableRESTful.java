package com.wesoon.web.mvc.annotation;

import com.wesoon.web.mvc.config.HttpResponseMaxLongConfig;
import com.wesoon.web.mvc.config.RestReturnValueHandler;
import com.wesoon.web.mvc.config.InitializationConfig;
import com.wesoon.web.mvc.config.WebConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({InitializationConfig.class, RestReturnValueHandler.class, HttpResponseMaxLongConfig.class, WebConfig.class})
@Documented
public @interface EnableRESTful {

}
