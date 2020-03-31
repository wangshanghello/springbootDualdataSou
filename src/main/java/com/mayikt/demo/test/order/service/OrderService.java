package com.mayikt.demo.test.order.service;

import com.mayikt.demo.test.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: OrderService
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2917:38
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public int inserOrder(String number) {
        return orderMapper.inserOrder(number);
    }
}
