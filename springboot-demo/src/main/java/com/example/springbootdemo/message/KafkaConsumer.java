package com.example.springbootdemo.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = {"testtopic"}, groupId = "test-kafka1")
    public void onMessage(ConsumerRecord<?, ?> record) {
        log.info("consume topic:{}, partition:{}, value:{}", record.topic(), record.partition(), record.value());
    }
}
