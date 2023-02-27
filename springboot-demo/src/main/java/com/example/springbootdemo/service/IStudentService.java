package com.example.springbootdemo.service;

import com.example.springbootdemo.model.Student;

public interface IStudentService {
    Student getStudentById(Long id);
    Student insert(Student student);
    int update(Student student);
    int deleteById(Long id);
}
