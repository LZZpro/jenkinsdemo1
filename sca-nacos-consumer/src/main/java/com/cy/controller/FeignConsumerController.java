package com.cy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/feign/")
public class FeignConsumerController {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private ConsumerHttpApi consumerHttpApi;
  /**基于feign方式的服务调用*/
  @GetMapping
  public String doFeignEcho(){
      return consumerHttpApi.echoMessage(appName);
  }

}
