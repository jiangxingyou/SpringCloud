package com.jxy8866.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/configInfo")
    public String fun1(){
        return "serverport:"+serverport+", configInfo:"+configInfo;
    }
}
