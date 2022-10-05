package com.jxy8866.service;


import com.jxy8866.entities.CommonResult;
import com.jxy8866.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回, ---PaymentFallbackService",
                new Payment(id, "serial"));
    }
}
