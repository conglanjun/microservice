package com.example.springbootdemo.service;

import com.example.springbootdemo.message.KafkaProducer;
import com.example.springbootdemo.model.City;
import com.example.springbootdemo.model.Student;
import com.example.springbootdemo.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private CityService cityService;

    @Override
    public Student getStudentById(Long id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public Student insert(Student student) {
        City city = cityService.getCityById(student.getCity().getId());
        student.setCity(city);
        int ret = studentMapper.insert(student);
        if (ret > 0) {
            log.info("insert successfully, ret={} and entity:{}", ret, student);
            kafkaProducer.sendMessage("testtopic", student);
            return student;
        }
        return null;
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int deleteById(Long id) {
        return studentMapper.deleteById(id);
    }
}
