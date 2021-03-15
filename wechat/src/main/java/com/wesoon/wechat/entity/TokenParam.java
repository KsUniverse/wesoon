package com.wesoon.wechat.entity;

import lombok.Data;

@Data
public class TokenParam {

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

}
