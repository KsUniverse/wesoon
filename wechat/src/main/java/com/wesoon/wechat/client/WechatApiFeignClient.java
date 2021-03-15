package com.wesoon.wechat.client;

import com.wesoon.wechat.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "wechat-api-feignclient", url = "https://api.weixin.qq.com")
public interface WechatApiFeignClient {

    @GetMapping("/cgi-bin/token")
    BasicAccessToken accessToken(@SpringQueryMap BasicAccessTokenParam param);

    @PostMapping("/cgi-bin/menu/create")
    WechatResponse menuCreate(@RequestParam("access_token") String accessToken, @RequestBody MenuParam param);

    @PostMapping("/cgi-bin/get_current_selfmenu_info")
    Map<String, Object> menuQuery(@RequestParam("access_token") String accessToken);

    @GetMapping("/cgi-bin/menu/delete")
    WechatResponse menuDelete(@RequestParam("access_token") String accessToken);

    @GetMapping("/sns/oauth2/access_token")
    OAuth2AccessToken oauth2AccessToken(@SpringQueryMap OAuth2AccessTokenParam accessTokenParam);

    @GetMapping("/sns/userinfo?access_token={accessToken}&openid={openId}&lang=zh_CN")
    WechatUserInfo oauth2UserInfo(@PathVariable String accessToken, @PathVariable String openId);
}
