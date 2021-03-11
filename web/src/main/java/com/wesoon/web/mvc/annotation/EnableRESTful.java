package com.wesoon.web.mvc.annotation;

import com.wesoon.web.mvc.handler.RestReturnValueHandler;
import com.wesoon.web.mvc.config.InitializationConfig;
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
@Import({InitializationConfig.class, RestReturnValueHandler.class})
@Documented
public @interface EnableRESTful {

}
