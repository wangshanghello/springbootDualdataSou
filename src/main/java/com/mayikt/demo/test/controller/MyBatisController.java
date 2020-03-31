package com.mayikt.demo.test.controller;

import com.mayikt.demo.test.member.service.MemberService;
import com.mayikt.demo.test.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MyBatisController
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2521:31
 */
@RestController
public class MyBatisController {
//    @Autowired
//    private MemberMapper memberMapper;
//    @Autowired
//    private OrderMapper orderMapper;


    private String userName;

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;
    /**
     * 调用我们的会员数据库
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(String name, Integer age) {
        return memberService.addUser(name, age) > 0 ? "success" : "fail";
    }

//    /**
//     * 调用order的数据库
//     *
//     * @param number
//     * @return
//     */
//    @RequestMapping("/inserOrder")
//    public String inserOrder(String number) {
//        return orderMapper.inserOrder(number) > 0 ? "success" : "fail";
//    }

/**
 *@Description:
 *@Param:
 *@return:
 *@Author: wangshang
 *@date: 2020/3/31
 *类作用:
*/
@RequestMapping("find")
public List<Map<String, Object>> index(){
    try {
       List<Map<String,Object>> map =  memberService.findUser();
        return map;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    @RequestMapping("/getUserName")
    public String getUserName() {

        return userName;
    }

}
