package com.example.springbootdemo.message;

import com.example.springbootdemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Student student) {
        kafkaTemplate.send(topic, String.valueOf(student.getId()), student);
    }
}
