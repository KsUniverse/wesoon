package com.wesoon.wechat;

import com.wesoon.web.exception.BusinessException;
import com.wesoon.wechat.client.WechatApiFeignClient;
import com.wesoon.wechat.config.WechatProperties;
import com.wesoon.wechat.entity.BasicAccessToken;
import com.wesoon.wechat.entity.BasicAccessTokenParam;
import com.wesoon.wechat.entity.OAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccessTokenProducer {

    private static final BasicAccessToken BASE_ACCESS_TOKEN = new BasicAccessToken();
    private static final Map<String, OAuth2AccessToken> OAUTH_ACCESS_TOKEN = new HashMap<>();

    private static WechatProperties wechatProperties;

    private static WechatApiFeignClient wechatApiFeignClient;

    @Autowired
    public AccessTokenProducer(WechatProperties wechatProperties,
                               WechatApiFeignClient wechatApiFeignClient) {
        AccessTokenProducer.wechatProperties = wechatProperties;
        AccessTokenProducer.wechatApiFeignClient = wechatApiFeignClient;
    }

    public static String baseAccessToken() {
        if(BASE_ACCESS_TOKEN.getValidTimestamp() < System.currentTimeMillis()) {
            BasicAccessTokenParam param = new BasicAccessTokenParam();
            param.setAppid(wechatProperties.getAppId());
            param.setSecret(wechatProperties.getAppsecret());
            param.setGrant_type("client_credential");
            BasicAccessToken accessTokenResult = wechatApiFeignClient.accessToken(param);
            if (accessTokenResult.getErrcode() != null) {
                throw new BusinessException(String.format("获取accessToken失败. errcode: [%s], errmsg: [%s]", BASE_ACCESS_TOKEN.getErrcode(), BASE_ACCESS_TOKEN.getErrmsg()));
            }
            BASE_ACCESS_TOKEN.setAccess_token(accessTokenResult.getAccess_token());
            BASE_ACCESS_TOKEN.setValidTimestamp(accessTokenResult.getExpires_in() * 1000 + System.currentTimeMillis() - 1000 * 60);
            BASE_ACCESS_TOKEN.setExpires_in(accessTokenResult.getExpires_in());
        }
        return BASE_ACCESS_TOKEN.getAccess_token();
    }
}
