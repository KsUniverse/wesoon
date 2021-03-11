package com.wesoon.web.common.page;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private List<T> data;

    private Long total;

    private Long page;

    private Long size;
}
