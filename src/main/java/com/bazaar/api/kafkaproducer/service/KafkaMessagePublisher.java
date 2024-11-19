package com.bazaar.api.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private final KafkaTemplate<String, Object> template;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void sendMessage(String topic, Object message) {
        CompletableFuture<SendResult<String, Object>> future = template.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message + "] to topic=[" + topic + "] " +
                        "with offset=[" + result.getRecordMetadata().offset() + "] " +
                        "stored at partition=[" + result.getRecordMetadata().partition() + "]");
            } else {
                System.out.println("Unable to send message=[" + message + "] to topic=[" + topic + "] " +
                        "because of :"+ex.getMessage());
            }
        });
    }

}