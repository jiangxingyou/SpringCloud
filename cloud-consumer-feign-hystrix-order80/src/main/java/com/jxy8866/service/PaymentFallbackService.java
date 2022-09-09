package com.jxy8866.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "---------服务调用失败，提示来自：cloud-consumer-feign-order80，paymentInfo_OK+o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---------服务调用失败，提示来自：cloud-consumer-feign-order80，paymentInfo_TimeOut+ o(╥﹏╥)o";
    }
}
