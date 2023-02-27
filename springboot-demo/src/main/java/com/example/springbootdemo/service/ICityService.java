package com.example.springbootdemo.service;

import com.example.springbootdemo.model.City;

import java.util.List;

public interface ICityService {
    List<City> findAll();
    City saveCity(City city);
    City getCityById(Long id);
}
