package com.profITsoft.profITsoft18_19.service.serviceInterface;

import com.profITsoft.profITsoft18_19.dto.MessageDto;

public interface MessageService {
    void saveMessage(MessageDto messageDto);
    void setMessageStatus(String messageID, String messageStatus);
}
