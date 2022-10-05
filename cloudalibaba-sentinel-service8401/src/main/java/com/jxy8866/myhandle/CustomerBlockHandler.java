package com.jxy8866.myhandle;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jxy8866.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(4444,"自定义的限流处理信息......CustomerBlockHandler----1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(4444,"自定义的限流处理信息......CustomerBlockHandler---2");
    }
}
