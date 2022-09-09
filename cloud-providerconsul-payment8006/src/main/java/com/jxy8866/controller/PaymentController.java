package com.jxy8866.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/consul")
    public String paymentInfo(){
        return "Spring cloud with consul:"+ serverport+"\t\t"+ UUID.randomUUID().toString();
    }
}
