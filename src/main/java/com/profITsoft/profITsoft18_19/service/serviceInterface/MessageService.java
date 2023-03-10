package com.profITsoft.profITsoft18_19.service.serviceInterface;

import com.profITsoft.profITsoft18_19.dto.MessageDto;
import com.profITsoft.profITsoft18_19.entity.Message;
import com.profITsoft.profITsoft18_19.entity.MessageStatus;
import com.profITsoft.profITsoft18_19.entity.enums.Status;
import com.profITsoft.profITsoft18_19.messaging.KafkaMessage;

import java.util.List;

public interface MessageService {
    void saveMessage(MessageDto messageDto);
    void  messageProcess(KafkaMessage kafkaMessage);
    void setMessageStatus(String messageID, MessageStatus messageStatus);
    Message getMessageByID(String id);
    List<Message> getAllMailWithWrongStatus();
    List<Message> allLettersWithEmailAndStatus(String email, Status status);
}
