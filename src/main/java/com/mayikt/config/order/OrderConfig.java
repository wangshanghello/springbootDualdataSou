package com.mayikt.config.order;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: OrderConfig
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2918:03
 */
@ConfigurationProperties(prefix = "spring.datasource.order")
@Data
public class OrderConfig {
    private String url;
    private String userName;
    private String passWord;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;

    private String uniqueResourceName;

}
