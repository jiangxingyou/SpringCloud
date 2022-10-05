package com.jxy8866.controller;

import com.jxy8866.domain.CommonResult;
import com.jxy8866.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping (value = "/account/decrease")
    public CommonResult create(@RequestParam("userId")Long userId, @RequestParam("money")BigDecimal money){
        int result = accountService.decrease(userId, money);
        return new CommonResult(200, "扣款成功!", result);
    }
}
