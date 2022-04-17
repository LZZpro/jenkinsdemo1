package com.cy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/consumer/")
public class RestConsumerController {
    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    @Value("${server.port}")
    private String server;

    @GetMapping("doLoadBalanced/")
    public String doRestLoadBalancedEcho(){
        // 假如RestTemplate由@LoadBalanced注解描述，在编写url时，应该用服务名调用
        String url = String.format("http://%s/provider/template/echo/%s","nacos-provider",server);
        return loadBalancedRestTemplate.getForObject(url,String.class);
    }


    @PostMapping
    public Map<String,Object> doPost(@RequestBody Map<String,Object> map){
        String url = String.format("http://%s/provider/template/","nacos-provider");

        return loadBalancedRestTemplate.postForObject(url,map,Map.class);
    }

}
