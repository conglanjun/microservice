package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    Student getStudentById(Long id);
    int insert(Student student);
    int update(Student student);
    int deleteById(Long id);
}
