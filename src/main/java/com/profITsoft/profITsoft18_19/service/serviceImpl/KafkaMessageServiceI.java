package com.profITsoft.profITsoft18_19.service.serviceImpl;

import com.profITsoft.profITsoft18_19.dto.MessageDto;
import com.profITsoft.profITsoft18_19.messaging.KafkaMessage;
import com.profITsoft.profITsoft18_19.service.serviceInterface.KafkaMessageService;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaMessageServiceI implements KafkaMessageService {

    private final KafkaOperations<String, KafkaMessage> kafkaOperations;

    public KafkaMessageServiceI(KafkaOperations<String, KafkaMessage> kafkaOperations) {
        this.kafkaOperations = kafkaOperations;
    }

    @Override
    public void sendMessage(String topic, MessageDto messageDto){
        kafkaOperations.send(topic, createKafkaMessage(messageDto));
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
