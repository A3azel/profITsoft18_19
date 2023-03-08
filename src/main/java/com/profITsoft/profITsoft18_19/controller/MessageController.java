package com.profITsoft.profITsoft18_19.controller;

import com.profITsoft.profITsoft18_19.dto.MessageDto;
import com.profITsoft.profITsoft18_19.exception.EntityValidationException;
import com.profITsoft.profITsoft18_19.service.serviceImpl.KafkaMessageServiceI;
import com.profITsoft.profITsoft18_19.validation.ErrorValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/message", produces="application/json")
public class MessageController {

    @Value("${kafka.topic.paymentReceived}")
    private String paymentReceivedTopic;

    private final KafkaMessageServiceI kafkaMessageServiceI;
    private final ErrorValidator errorValidator;

    public MessageController(KafkaMessageServiceI kafkaMessageServiceI, ErrorValidator errorValidator) {
        this.kafkaMessageServiceI = kafkaMessageServiceI;
        this.errorValidator = errorValidator;
    }


    @PostMapping("/message/confirmation")
    public void receivePayment(@RequestBody MessageDto messageDto, BindingResult bindingResult) {
        String errorMassage = errorValidator.checkErrors(bindingResult);
        if(!errorMassage.equals("")){
            throw new EntityValidationException(errorMassage);
        }
        kafkaMessageServiceI.sendMessage(paymentReceivedTopic, messageDto);
    }
}
