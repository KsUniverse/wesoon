package com.wesoon.wechat.entity;

import lombok.Data;

import java.util.List;

@Data
public class WechatUserInfo {

    private String openid;

    private String nickname;

    private Integer sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private List<String> privilege;

    private String unionid;

    private OAuth2AccessToken oAuth2AccessToken;

}
