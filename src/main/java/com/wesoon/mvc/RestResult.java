package com.wesoon.mvc;

import lombok.Data;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Data
public class RestResult {

    private boolean isSuccess;
    private int code;
    private String data;
    private String desc;
}
