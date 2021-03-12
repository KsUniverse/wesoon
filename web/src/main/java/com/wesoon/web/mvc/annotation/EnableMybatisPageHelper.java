package com.wesoon.web.mvc.annotation;


import com.wesoon.web.mvc.config.MybatisPlusConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(MybatisPlusConfig.class)
@Documented
public @interface EnableMybatisPageHelper {

}
