package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.City;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
