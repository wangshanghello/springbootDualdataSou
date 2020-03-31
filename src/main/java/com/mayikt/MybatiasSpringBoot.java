package com.mayikt;

import com.mayikt.config.member.MemberConfig;
import com.mayikt.config.order.OrderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MybatiasSpringBoort
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2521:34
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({OrderConfig.class, MemberConfig.class})
public class MybatiasSpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(MybatiasSpringBoot.class);
    }
}
