package com.jxy8866.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


public interface AccountService {

    int decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money);
}
