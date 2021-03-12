package com.wesoon.web.mvc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag = 0;

    private Date createTime;

    private Long createUser;

    private Date UpdateTime;

    private Long updateUser;
}
