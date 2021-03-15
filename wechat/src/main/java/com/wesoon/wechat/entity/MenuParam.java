package com.wesoon.wechat.entity;

import lombok.Data;

import java.util.List;

@Data
public class MenuParam {

    private List<Button> button;

    @Data
    public static class Button {

        private String type;
        private String name;

        private String key;
        private String url;
        private String media_id;
        private String appid;
        private String pagepath;

        private List<Button> sub_button;
    }
}
