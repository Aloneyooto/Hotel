package com.alone.hotel.config.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.config.service
 * @Author: Alone
 * @CreateTime: 2020-03-09 08:23
 * @Description: 配置事务管理器
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagementConfig {
    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource")DataSource myDataSource){
        return new DataSourceTransactionManager(myDataSource);
    }
}
