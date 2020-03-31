package com.mayikt.config.member;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberDataSourceConfig
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2521:23
 */
@Configuration
@MapperScan(basePackages = "com.mayikt.demo.test.member.mapper", sqlSessionTemplateRef = "memberSqlSessionTemplate")
public class MemberDataSourceConfig {
    //@Configuration xml  MemberDataSourceConfig.xml

    /**
     * 创建我们的DataSource
     *
     * @return
     */
    @Bean("memberDataSource")
    public DataSource memberDataSource(MemberConfig memberConfig) throws SQLException {
        // 1.创建MysqlXADataSource
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(memberConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(memberConfig.getPassWord());
        mysqlXaDataSource.setUser(memberConfig.getUserName());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);


        // 2.将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(memberConfig.getUniqueResourceName());
        xaDataSource.setMinPoolSize(memberConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(memberConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(memberConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(memberConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(memberConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(memberConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(memberConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(memberConfig.getTestQuery());
        return xaDataSource;
    }

    /**
     * 创建我们的SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "memberSqlSessionFactory")
    public SqlSessionFactory memberSqlSessionFactory(@Qualifier("memberDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

//    /**
//     * 创建会员管理器
//     *
//     * @param dataSource
//     * @return
//     */
//    @Bean(name = "memberTransactionManager")
//    public DataSourceTransactionManager memberTransactionManager(@Qualifier("memberDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    /**
     * 创建订单sqlSesion模版
     *
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "memberSqlSessionTemplate")
    public SqlSessionTemplate memberSqlSessionTemplate(
            @Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
