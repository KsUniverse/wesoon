package com.wesoon.wechat;

import com.wesoon.web.exception.BusinessException;
import com.wesoon.web.mvc.annotation.other.OnRestReturn;
import com.wesoon.wechat.client.WechatApiFeignClient;
import com.wesoon.wechat.config.WechatProperties;
import com.wesoon.wechat.entity.MenuParam;
import com.wesoon.wechat.entity.WechatResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("微信-菜单模块")
@RestController
@RequestMapping("/wechat/menu")
public class MenuController {

    private WechatApiFeignClient wechatApiFeignClient;
    private WechatProperties wechatProperties;

    public MenuController(WechatApiFeignClient wechatApiFeignClient, WechatProperties wechatProperties) {
        this.wechatApiFeignClient = wechatApiFeignClient;
        this.wechatProperties = wechatProperties;
    }

    @ApiOperation("创建菜单")
    @OnRestReturn
    @PostMapping("/create")
    public void create(@RequestBody MenuParam menuParam) {
        WechatResponse response = wechatApiFeignClient.menuCreate(AccessTokenProducer.baseAccessToken(), menuParam);
        if (!"0".equals(response.getErrcode())) {
            throw new BusinessException(response.getErrmsg());
        }
    }

    @ApiOperation("查询菜单")
    @OnRestReturn
    @GetMapping("/query")
    public Map<String, Object> query() {
        return wechatApiFeignClient.menuQuery(AccessTokenProducer.baseAccessToken());
    }

    @ApiOperation("删除菜单")
    @OnRestReturn
    @GetMapping("/delete")
    public void delete() {
        WechatResponse response = wechatApiFeignClient.menuDelete(AccessTokenProducer.baseAccessToken());
        if (!"0".equals(response.getErrcode())) {
            throw new BusinessException(response.getErrmsg());
        }
    }
}
