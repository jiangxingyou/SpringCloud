package com.jxy8866.service.impl;

import com.jxy8866.dao.AccountDao;
import com.jxy8866.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;


    @Override
    public int decrease(Long userId, BigDecimal money) {
        log.info("----------> 扣减账号start");
        //模拟超时异常，20S；需要--全局事务回滚；
/*        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        int result = accountDao.decrease(userId, money);
        log.info("----------> 扣减账号end");
        return result;
    }
}
