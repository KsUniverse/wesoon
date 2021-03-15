package com.wesoon.wechat.entity;

import lombok.Data;

@Data
public class OAuth2AccessTokenParam {

    private String appid;

    private String secret;

    private String grant_type = "authorization_code";

    private String code;
}
