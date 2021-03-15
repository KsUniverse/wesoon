package com.wesoon.wechat.entity;

import lombok.Data;

@Data
public class BasicAccessTokenParam {

    private String grant_type;

    private String appid;

    private String secret;
}
