package com.profITsoft.profITsoft18_19.controller;

import com.profITsoft.profITsoft18_19.dto.MessageDto;
import com.profITsoft.profITsoft18_19.messaging.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/message", produces="application/json")
public class MessageController {

    @Value("${kafka.topic.paymentReceived}")
    private String paymentReceivedTopic;

    private final KafkaOperations<String, KafkaMessage> kafkaOperations;

    @Autowired
    public MessageController(KafkaOperations<String, KafkaMessage> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    @PostMapping("/message/confirmation")
    public void receivePayment(@RequestBody MessageDto messageDto) {
        kafkaOperations.send(paymentReceivedTopic, createKafkaMessage(messageDto));
    }

    private KafkaMessage createKafkaMessage(MessageDto messageDto){
        return KafkaMessage.builder()
                .transactionId(UUID.randomUUID().toString())
                .subject(messageDto.getSubject())
                .content(messageDto.getContent())
                .email(messageDto.getEmail())
                .build();
    }

}
