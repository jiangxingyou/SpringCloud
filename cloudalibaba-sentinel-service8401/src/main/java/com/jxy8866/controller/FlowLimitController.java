package com.jxy8866.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA() throws InterruptedException {
//        TimeUnit.MILLISECONDS.sleep(800);
        log.info("当前线程："+Thread.currentThread().getName()+"--------testA");
        return "当前线程："+Thread.currentThread().getName()+"--------testA";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "当前线程："+Thread.currentThread().getName()+"--------testB";
    }


    @GetMapping("/testD")
    public String testD()
    {
        //暂停几秒钟线程
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
        int age = 10/0;
        return "------testD";
    }


    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHoyKey", blockHandler = "default_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2)
    {
        return "当前线程："+Thread.currentThread().getName()+"--------testHotKey, O(∩_∩)O哈哈~";
    }

    public String default_testHotKey(String p1, String p2, BlockException e)
    {
        return "当前线程："+Thread.currentThread().getName()+"--------default_testHotKey, o(╥﹏╥)o！";
    }
}
