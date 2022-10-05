package com.jxy8866.service;


import com.jxy8866.entities.CommonResult;
import com.jxy8866.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
//feign就是客户端，接口对接口访问的，封装了restTemplate和ribbon吧
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
