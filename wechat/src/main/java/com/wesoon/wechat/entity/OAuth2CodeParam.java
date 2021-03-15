package com.wesoon.wechat.entity;

import lombok.Data;

@Data
public class OAuth2CodeParam {

    private String appid;

    private String redirect_uri;

    private String response_type = "code";

    private String scope = "snsapi_userinfo";

    private String state;
}
