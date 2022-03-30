package com.kafka.kafka.controller;

import com.kafka.kafka.dto.KMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/kmessage")
public class ResourceController {

    @Value("${demo.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, KMessage> kafkaTemplate;

    public ResourceController(KafkaTemplate<String, KMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendMessage(@RequestBody KMessage kMessage) {
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), kMessage);
    }
}
