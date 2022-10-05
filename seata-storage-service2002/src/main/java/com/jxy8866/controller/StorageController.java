package com.jxy8866.controller;

import com.jxy8866.domain.CommonResult;
import com.jxy8866.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

//    @GetMapping(value = "/storage/decrease") 报错：status 405 reading StorageService#decrease(Long,Integer)
    @RequestMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        log.info("-------扣减库存开始---------");
        int result = storageService.decrease(productId, count);
        log.info("-------扣减库存结束---------");
        return new CommonResult(200, "减少库存成功！", result);
    }

}
