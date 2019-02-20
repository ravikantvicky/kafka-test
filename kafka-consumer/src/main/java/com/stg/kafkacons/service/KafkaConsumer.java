package com.stg.kafkacons.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	@KafkaListener(topics = "testdata")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
