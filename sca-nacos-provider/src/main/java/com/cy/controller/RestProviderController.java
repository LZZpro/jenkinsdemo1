package com.cy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@RequestMapping("/provider/template/")
public class RestProviderController {


    @Value("${server.port}")
    private String server;

    @GetMapping("echo/{string}")
    public String doEcho(@PathVariable String string){
        return "生产者:"+server+"-> say:Hello 消费者 "+string;
    }

    @PostMapping
    public Map<String,Object> doPost(@RequestBody Map<String,Object> map){
        System.out.println("consumer post data:"+map);
        map.put("status",1);
        map.put("server.port",server);
        return map;
    }

}
