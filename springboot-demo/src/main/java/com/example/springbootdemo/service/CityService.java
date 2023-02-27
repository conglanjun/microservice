package com.example.springbootdemo.service;

import com.example.springbootdemo.mapper.CityMapper;
import com.example.springbootdemo.model.City;
import com.example.springbootdemo.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityService implements ICityService{
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public List<City> findAll() {
//        return (List<City>) cityRepository.findAll();
        return null;
    }

    @Override
    public City saveCity(City city) {
//        return cityRepository.save(city);
        return null;
    }

    @Override
    public City getCityById(Long id) {
        City city = cityMapper.getCityById(id);
        if (city != null) {
            // set redis
            redisUtils.set(city.getId().toString(), city.getName());
            return city;
        }
        return null;
    }
}
