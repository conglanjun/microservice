package com.example.springbootdemo.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource(value={"classpath:application.yml"})
@MapperScan(basePackages = "com.example.springbootdemo.mapper", sqlSessionFactoryRef = "pgsqlSqlSessionFactory")
public class PgSqlDataSourceConfig {
//    @Bean(name = "pgsqlDataSource")
//    @ConfigurationProperties("spring.datasource.pgsql")
//    public DataSource masterDataSource(){
//        return DataSourceBuilder.create().build();
//    }

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(10);
        datasource.setMinIdle(10);
        datasource.setMaxActive(200);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(45000);
        datasource.setValidationQuery("SELECT 1");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(true);
        datasource.setTestOnReturn(false);
        datasource.setRemoveAbandonedTimeout(3600);
        try {
            datasource.setFilters("stat");
            datasource.init();
        } catch (SQLException e) {
            System.out.println("druid configuration initialization filter");
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean(name = "pgsqlSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(druidDataSource());
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        sessionFactoryBean.setTypeAliasesPackage("com.example.springbootdemo.model");
        sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis/mybatis-config.xml"));
        return sessionFactoryBean.getObject();
    }
}
