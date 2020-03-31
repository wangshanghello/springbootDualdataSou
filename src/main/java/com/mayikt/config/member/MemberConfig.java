package com.mayikt.config.member;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberConfig
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2918:08
 */
@ConfigurationProperties(prefix = "spring.datasource.member")
@Data
public class MemberConfig {
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
