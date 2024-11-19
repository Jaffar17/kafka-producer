package com.bazaar.api.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic createTopic() {
        return new NewTopic("learning-kafka", 3, (short) 1);
    }
}
