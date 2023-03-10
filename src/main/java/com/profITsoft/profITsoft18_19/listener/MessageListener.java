package com.profITsoft.profITsoft18_19.listener;

import com.profITsoft.profITsoft18_19.messaging.KafkaMessage;
import com.profITsoft.profITsoft18_19.service.serviceInterface.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final MessageService messageService;

    @Autowired
    public MessageListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "${kafka.topic.paymentReceived}")
    public void paymentReceived(KafkaMessage kafkaMessage) {
        messageService.messageProcess(kafkaMessage);
    }


}
