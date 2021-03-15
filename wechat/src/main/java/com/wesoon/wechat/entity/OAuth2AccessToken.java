package com.wesoon.wechat.entity;

import lombok.Data;

@Data
public class OAuth2AccessToken extends WechatResponse {

    private String access_token = "43_mUlxoYOKrTqL6ezReG06LndIWdP1Itn366acMwbmP5MN0t9KktvNwAxV25-hfEmprDDq6qTWfcT8BcDqR6MCHSOicFMIC24yxFHDYEppru9DzBQ-7l7QRvHDiRl38WW8wO0gZ3OH4VhWiFbkYWCgAEASQW";

    private Long expires_in;

    private String refresh_token;

    private String openid;

    private String scope;

    //save valid timestamp
    private Long validTimestamp = 1616635182000L;
}
