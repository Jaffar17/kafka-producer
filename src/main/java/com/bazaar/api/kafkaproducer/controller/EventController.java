package com.bazaar.api.kafkaproducer.controller;

import com.bazaar.api.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka-producer")
public class EventController {
    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            String topic = "learning-kafka";
            for(int i=0; i<=100000; i++){
                publisher.sendMessage(topic, message+":"+i);
            }
            return ResponseEntity.ok("Message published");
        } catch (Exception e) {
            System.out.println("Error publishing message: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
