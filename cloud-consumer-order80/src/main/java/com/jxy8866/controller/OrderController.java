package com.jxy8866.controller;

import com.jxy8866.LoadBalance.LoadBalancer;
import com.jxy8866.entities.CommonResult;
import com.jxy8866.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;


/**
 * 顺序控制器
 *
 * @author JXY02
 * @date 2022/09/03 09:09:06
 */
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    // 通过在eureka上注册过的微服务名称调用
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        CommonResult<Payment> commonResult = restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
        log.info("调用8001服务接口，result："+commonResult);
        return commonResult;
    }

    @GetMapping("/consumer/payment/create2")
    public CommonResult<Payment> create2(Payment payment){
        CommonResult<Payment> commonResult = restTemplate.postForEntity(PAYMENT_URL+"/payment/create", payment, CommonResult.class).getBody();
        log.info("调用8001服务接口，result："+commonResult);
        return commonResult;
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

    //2022年9月2日--getForEntity
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id")Long id){
        ResponseEntity<CommonResult> entity =  restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            //2Xx,表示是正确的；
            log.info(entity.getStatusCode().toString());
            return entity.getBody();
        }else {
            return new CommonResult<>(444, "操作失败！");
        }

    }

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;


    /**
     * 手写ribbon轮询算法，验证
     *
     * @param id id
     * @return {@link CommonResult}<{@link Payment}>
     */
    @GetMapping("/consumer/payment/getLB/{id}")
    public CommonResult<Payment> getPaymentLB(@PathVariable("id")Long id){
        //获取当前所有的服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0){
            return null;
        }
        //使用自定义轮询算法，输入当前的server instance list，输出下一个服务实例。
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        System.out.println("URI:"+uri);//URI:http://192.168.0.105:8002
        return restTemplate.getForObject(uri+"/payment/get/"+id, CommonResult.class);
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject( PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }

}
