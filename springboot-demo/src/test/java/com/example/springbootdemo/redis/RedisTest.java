package com.example.springbootdemo.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;
    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("redis_key1", "redis_vale1");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtils.get("redis_key");
        System.out.println(value);
    }
}
