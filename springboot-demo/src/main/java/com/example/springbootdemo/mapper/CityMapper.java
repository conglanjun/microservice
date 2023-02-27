package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
    City getCityById(Long id);
}
