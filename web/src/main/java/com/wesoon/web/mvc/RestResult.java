package com.wesoon.web.mvc;

import lombok.Data;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Data
public class RestResult {

    private boolean success;
    private int code;
    private Object data;
    private String desc;
}
