package com.jxy8866.controller;

import com.jxy8866.entities.CommonResult;
import com.jxy8866.entities.Payment;
import com.jxy8866.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*********插入结果："+result+"\t"+"哈哈！----2022年8月24日"+"\nsmile");
        if (result > 0){
            return new CommonResult(200, "插入数据数据成功, serverPort："+serverPort, result);
        }else {
            return new CommonResult(444, "插入数据库失败, serverPort:"+serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*********查询结果："+payment);
        if (payment != null){
            return new CommonResult(200, "查询数据数据成功,serverPort:"+serverPort, payment);
        }else {
            return new CommonResult(444, "没有对应记录，查询ID："+id+",serverPort:"+serverPort, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        for(String element:list){
            log.info("********element:"+element);
        }
        List<ServiceInstance> list2 = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance element:list2){
            log.info("*********element:"+element.toString());
            log.info("id:"+element.getServiceId()+", host:"+element.getHost()+", port:" +
                    ""+element.getPort()+", url:"+element.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/lb")
    public String paymentLB(){
        return serverPort;
    }
}
