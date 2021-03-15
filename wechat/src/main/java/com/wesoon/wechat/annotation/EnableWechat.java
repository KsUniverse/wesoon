package com.wesoon.wechat.annotation;


import com.wesoon.wechat.AccessTokenProducer;
import com.wesoon.wechat.config.ControllerConfiguration;
import com.wesoon.wechat.config.WechatProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ControllerConfiguration.class, WechatProperties.class, AccessTokenProducer.class})
@Documented
public @interface EnableWechat {

}
