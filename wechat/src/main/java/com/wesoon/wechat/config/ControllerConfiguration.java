package com.wesoon.wechat.config;

import com.wesoon.wechat.MenuController;
import com.wesoon.wechat.TokenController;
import com.wesoon.wechat.client.WechatApiFeignClient;
import com.wesoon.wechat.client.WechatOpenFeignClient;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public TokenController tokenController(WechatProperties wechatProperties,
                                           WechatApiFeignClient wechatApiFeignClient) {
        return new TokenController(wechatProperties, wechatApiFeignClient);
    }

    @Bean
    public MenuController menuController(WechatApiFeignClient wechatApiFeignClient, WechatProperties wechatProperties) {
        return new MenuController(wechatApiFeignClient, wechatProperties);
    }

    @Bean
    public Decoder feignDecoder() {
        WxMessageConverter wxConverter = new WxMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(wxConverter);
        return new SpringDecoder(objectFactory);
    }
}
