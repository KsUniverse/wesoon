package com.wesoon.web.common.page;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class PageParam {

    @NotNull(message = "分页参数不能为空")
    private Long page = 1L;

    @NotNull(message = "分页参数不能为空")
    private Long size = 15L;
}
