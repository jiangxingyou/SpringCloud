package com.jxy8866;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients//使用一个组件，首先需要激活并开启，需要需要enableFeign
public class OrderFeignMain80 {
    public static void main(String[] args) {

        SpringApplication.run(OrderFeignMain80.class, args);
        System.out.println("Hello world!");
    }
}