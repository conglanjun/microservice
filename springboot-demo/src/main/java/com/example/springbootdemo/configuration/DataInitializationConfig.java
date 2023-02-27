package com.example.springbootdemo.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataInitializationConfig {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void init() {
        // 项目启动初始化基本数据
        // 通过直接读取sql文件执行
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScripts(new ClassPathResource("sql/schema-postgres.sql"));
        resourceDatabasePopulator.addScripts(new ClassPathResource("sql/data-postgres.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}