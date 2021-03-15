package com.wesoon.wechat.entity;

import lombok.Data;

@Data
public class BasicAccessToken extends WechatResponse {

    private String access_token = "43_gD6KigAuqlDfqThzKHscHMPZmbe0TTIIptzdg6gPWdh4inHLMgWF4xvjC_Ct6-_8ZK3MWdsDPt3vgzMPsFvRvhC2OVyFauaOCrQNDUMcmC69DlkukxB3sMioiVcZH3gREyRIFNDXTRKqUmUMECJgACAAYF";

    private Long expires_in;

    //save valid timestamp
    private Long validTimestamp = 1616635182000L;

}
