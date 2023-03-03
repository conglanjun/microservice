package com.example.springbootjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class SpringbootJpaApplication {

    public static void main(String[] args) throws SQLException {
        Logger logger = LoggerFactory.getLogger(SpringbootJpaApplication.class);
        logger.info("create database if not exist...");
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:15432/", "postgres", "postgres");
            statement = connection.createStatement();
            statement.executeQuery("select count(*) from pg_database where datname = 'jpa'");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count <= 0) {
                statement.executeUpdate("create database jpa");
                logger.info("data base created.");
            }
        } finally {
            if (statement != null) {
                statement.close();
                logger.debug("Closed Statement.");
            }
            if (connection != null) {
                logger.debug("Closed Connection.");
                connection.close();
            }
        }
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

}
