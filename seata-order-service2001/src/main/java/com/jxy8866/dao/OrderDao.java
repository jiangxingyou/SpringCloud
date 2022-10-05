package com.jxy8866.dao;


import com.jxy8866.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper//亦可以添加全局扫描
//这里使用的是mybatis；亦可以使用mybatis-plus；亦可以使用easycode，来生成基本的CRUD；
public interface OrderDao {

    /**
     * 创建订单
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态
     * @param userId
     * @param status
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
