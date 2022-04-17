package com.cy.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * 定义Http请求API，基于此API借助OpenFeign访问远端服务
 */
@FeignClient(name = "nacos-provider")
@RestController
interface ConsumerHttpApi {

    @GetMapping("/provider/echo/{string}")
    public String echoMessage(@PathVariable("string") String string);

}
