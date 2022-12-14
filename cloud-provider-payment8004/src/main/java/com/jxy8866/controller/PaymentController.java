package com.jxy8866.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverport;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk(){
        return "Springcloud with zookeeper:"+serverport+"\t"+ UUID.randomUUID().toString();
    }
}
