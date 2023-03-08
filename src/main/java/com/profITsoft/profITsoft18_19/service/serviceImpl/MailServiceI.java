package com.profITsoft.profITsoft18_19.service.serviceImpl;

import com.profITsoft.profITsoft18_19.exception.MailSandingError;
import com.profITsoft.profITsoft18_19.service.serviceInterface.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceI implements MailService {
    @Value("${custom.mail.address}")
    private String mailAddress;

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceI(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        log.debug("In the sendSimpleMessage method");
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailAddress);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            log.info("Message send...");
        }catch (Exception e){
            throw new MailSandingError(e.getClass() + ": " + e.getMessage());
        }
        log.debug("End of sendSimpleMessage method");
    }
}
