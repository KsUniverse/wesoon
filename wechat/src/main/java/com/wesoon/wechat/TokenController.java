package com.wesoon.wechat;

import com.wesoon.web.common.BasicDataTypeUtil;
import com.wesoon.web.exception.BusinessException;
import com.wesoon.web.mvc.annotation.other.OnRestReturn;
import com.wesoon.wechat.client.WechatApiFeignClient;
import com.wesoon.wechat.config.WechatProperties;
import com.wesoon.wechat.entity.OAuth2AccessToken;
import com.wesoon.wechat.entity.OAuth2AccessTokenParam;
import com.wesoon.wechat.entity.OAuth2CodeParam;
import com.wesoon.wechat.entity.TokenParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Api("微信-Token模块")
@Controller
@RequestMapping("/wechat")
public class TokenController {


    private WechatProperties wechatProperties;
    private WechatApiFeignClient wechatApiFeignClient;

    public TokenController(WechatProperties wechatProperties,
                           WechatApiFeignClient wechatApiFeignClient) {
        this.wechatProperties = wechatProperties;
        this.wechatApiFeignClient = wechatApiFeignClient;
    }

    @ApiOperation("接入服务器的token验证")
    @ResponseBody
    @RequestMapping("/token")
    public String tokenValid(TokenParam tokenParam) {
        String[] arr = new String[] {wechatProperties.getToken(), tokenParam.getTimestamp(), tokenParam.getNonce()};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String s : arr) {
            content.append(s);
        }
        String tmpStr = null;

        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] digest = instance.digest(content.toString().getBytes());
            tmpStr = BasicDataTypeUtil.byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(tokenParam.getSignature().toUpperCase().equals(tmpStr)) {
            return tokenParam.getEchostr();
        }
        return null;
    }

    private static final String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appId={appId}&redirect_uri={redirectUrl}&response_type={responseType}&scope={scope}&state={state}#wechat_redirect";

    @ApiOperation("oauth调起微信网页授权入口")
    @GetMapping("/oauth2/main")
    public String oauth2Main(@RequestParam("redirectUrl") String redirectUrl) {
        OAuth2CodeParam param = new OAuth2CodeParam();
        param.setAppid(wechatProperties.getAppId());
        if(!StringUtils.hasText(wechatProperties.getServerAddress())) {
            throw new BusinessException("微信网页授权, 必须配置serverAddress参数(服务器访问域名/端口). 以供微信回调");
        }
        param.setRedirect_uri(wechatProperties.getServerAddress() + "/wechat/oauth2/accessToken");
        param.setState(redirectUrl);
        String url = oauth2Url.replace("{appId}", param.getAppid())
                .replace("{redirectUrl}", param.getRedirect_uri())
                .replace("{responseType}", param.getResponse_type())
                .replace("{scope}", param.getScope())
                .replace("{state}", param.getState());
        return "redirect:" + url;
    }


    @ApiOperation("oauth获取code回调地址")
    @GetMapping("/oauth2/accessToken")
    public String oauth2AccessToken(@RequestParam("code") String code, @RequestParam("state") String state) {
        OAuth2AccessTokenParam accessTokenParam = new OAuth2AccessTokenParam();
        accessTokenParam.setAppid(wechatProperties.getAppId());
        accessTokenParam.setCode(code);
        accessTokenParam.setSecret(wechatProperties.getAppsecret());
        OAuth2AccessToken oAuth2AccessToken = wechatApiFeignClient.oauth2AccessToken(accessTokenParam);


        if(state.contains("?")) {
            state = state.replace("?", String.format("?openId=%s&", oAuth2AccessToken.getOpenid()));
        } else {
            state += String.format("?openId=%s", oAuth2AccessToken.getOpenid());
        }
        return String.format("redirect:%s", state);
    }
}
