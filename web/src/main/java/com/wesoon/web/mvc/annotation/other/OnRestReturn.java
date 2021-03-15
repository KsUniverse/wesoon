package com.wesoon.web.mvc.annotation.other;



import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnRestReturn {
}
