package com.jxy8866.service.impl;

import com.jxy8866.dao.OrderDao;
import com.jxy8866.domain.Order;
import com.jxy8866.service.AccountService;
import com.jxy8866.service.OrderService;
import com.jxy8866.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //1.新建订单
        log.info("----开始新建订单----");
        orderDao.create(order);
        //2.扣减库存
        log.info("--------订单微服务开始调用库存，做扣减库存--start");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("--------订单微服务开始调用库存，做扣减库存--end");

        //3. 扣减余额
        log.info("--------订单微服务开始调用账户，做扣减余额--start");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("--------订单微服务开始调用账户，做扣减余额--end");

        //4. 修改订单状态
        log.info("----修改新建订单----start");
        orderDao.update(order.getUserId(), 0);
        log.info("---下订单结束-------O(∩_∩)O哈哈~");
    }
}
