package com.profITsoft.profITsoft18_19.listener;

import com.profITsoft.profITsoft18_19.dto.MessageDto;
import com.profITsoft.profITsoft18_19.service.serviceInterface.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final MailService mailService;

    @Autowired
    public MessageListener(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(topics = "${kafka.topic.paymentReceived}")
    public void paymentReceived(MessageDto message) {
        //orderService.processPaymentReceived(message);
    }


}
