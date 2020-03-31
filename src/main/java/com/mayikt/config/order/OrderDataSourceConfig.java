package com.mayikt.config.order;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: orderDataSourceConfig
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/9/2521:23
 */
@Configuration
@MapperScan(basePackages = "com.mayikt.demo.test.order.mapper", sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderDataSourceConfig {
    //@Configuration xml  orderDataSourceConfig.xml

    /**
     * 创建我们的DataSource
     *
     * @return
     */
    @Bean("orderDataSource")
    public DataSource orderDataSource(OrderConfig orderConfig) throws SQLException {
        // 1.创建MysqlXADataSource
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(orderConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(orderConfig.getPassWord());
        mysqlXaDataSource.setUser(orderConfig.getUserName());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);


        // 2.将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(orderConfig.getUniqueResourceName());
        xaDataSource.setMinPoolSize(orderConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(orderConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(orderConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(orderConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(orderConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(orderConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(orderConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(orderConfig.getTestQuery());
        return xaDataSource;
    }

    /**
     * 创建我们的SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "orderSqlSessionFactory")
    @Primary
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
//
//    /**
//     * 创建会员管理器
//     *
//     * @param dataSource
//     * @return
//     */
//    @Bean(name = "orderTransactionManager")
//    public DataSourceTransactionManager orderTransactionManager(@Qualifier("orderDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    /**
     * 创建订单sqlSesion模版
     *
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(
            @Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
