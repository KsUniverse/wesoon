package com.wesoon.web.mvc.annotation;


import com.wesoon.web.mvc.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ErrorMessage.class, GlobalExceptionHandler.class})
@Documented
public @interface EnableGlobalError {

}
