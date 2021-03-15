package com.wesoon.wechat.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "wechat-open-feignclient", url = "https://open.weixin.qq.com")
public interface WechatOpenFeignClient {

}
