package com.profITsoft.profITsoft18_19.service.serviceInterface;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment,String fileName) throws MessagingException;
}
