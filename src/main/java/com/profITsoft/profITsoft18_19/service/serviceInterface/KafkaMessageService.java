package com.profITsoft.profITsoft18_19.service.serviceInterface;

import com.profITsoft.profITsoft18_19.dto.MessageDto;

public interface KafkaMessageService {
    void sendMessage(String topic, MessageDto messageDto);
}
