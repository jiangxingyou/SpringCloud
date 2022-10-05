package com.jxy8866.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.jxy8866.dao"})
public class MyBatisConfig {
}
