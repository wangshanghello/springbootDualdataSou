package com.mayikt.demo.test.member.service;

import com.mayikt.demo.test.member.mapper.MemberMapper;
import com.mayikt.demo.test.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberService
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2917:41
 */
@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Transactional()
    public int addUser(String name, Integer age) {
        int memberJ = memberMapper.addUser(name, age);
        int orderI = orderMapper.inserOrder(name);
        int j = 1 / age;
        return memberJ + orderI;
    }

    public List<Map<String,Object>> findUser() {
        return orderMapper.findUser();
    }
}
