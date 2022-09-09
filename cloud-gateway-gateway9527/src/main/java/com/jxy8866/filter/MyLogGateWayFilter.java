package com.jxy8866.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("***********come in MyLogGateWayFilter:"+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null){
            log.info("******uname:"+ null +", 为非法用户，o(╥﹏╥)o");
            //给一个回应，不被接受的
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }else{
            log.info("******uname:"+ uname +", 为正常用户，O(∩_∩)O哈哈~");
            //给一个回应，不被接受的
            exchange.getResponse().setStatusCode(HttpStatus.ACCEPTED);
            return chain.filter(exchange);
        }
//        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
