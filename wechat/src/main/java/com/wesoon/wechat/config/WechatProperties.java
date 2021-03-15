package com.wesoon.wechat.config;

import com.wesoon.wechat.client.WechatApiFeignClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableFeignClients(basePackageClasses = WechatApiFeignClient.class)
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {

    private String token;

    private String appId;

    private String appsecret;

    private Boolean enableRedis;

    private String serverAddress;
}
