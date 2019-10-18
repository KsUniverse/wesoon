package com.wesoon.web.annotation;


import com.wesoon.web.config.ErrorMessage;
import com.wesoon.web.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ErrorMessage.class, GlobalExceptionHandler.class})
@Documented
public @interface EnableGlobalError {

}
