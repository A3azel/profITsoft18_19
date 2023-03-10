package com.profITsoft.profITsoft18_19.service.serviceImpl;

import com.profITsoft.profITsoft18_19.dto.MessageDto;
import com.profITsoft.profITsoft18_19.entity.Message;
import com.profITsoft.profITsoft18_19.entity.MessageStatus;
import com.profITsoft.profITsoft18_19.entity.enums.Status;
import com.profITsoft.profITsoft18_19.exception.MailSandingError;
import com.profITsoft.profITsoft18_19.messaging.KafkaMessage;
import com.profITsoft.profITsoft18_19.repository.MongoTestRepo;
import com.profITsoft.profITsoft18_19.service.serviceInterface.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class MessageServiceI implements MessageService {

    private final MailServiceI mailServiceI;
    private final MongoTestRepo messageRepository;

    @Autowired
    public MessageServiceI(MailServiceI mailServiceI, MongoTestRepo messageRepository) {
        this.mailServiceI = mailServiceI;
        this.messageRepository = messageRepository;
    }

//
    @Override
    public void saveMessage(MessageDto messageDto) {

    }

    @Override
    public void messageProcess(KafkaMessage kafkaMessage) {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setEmail(kafkaMessage.getEmail());
        message.setSubject(kafkaMessage.getSubject());
        message.setContent(kafkaMessage.getContent());
        message.setMassageStatus(
                MessageStatus.builder()
                        .status(Status.RECEIVED)
                .build());
        messageRepository.save(message);
        sendingMessageLetter(message);
    }

    @Override
    public void setMessageStatus(String messageID, MessageStatus messageStatus) {
        Message updatedMessage = getMessageByID(messageID);
        updatedMessage.setMassageStatus(messageStatus);
        messageRepository.save(updatedMessage);
    }

    @Override
    public Message getMessageByID(String id) {
        return messageRepository.findMessageById(id);
    }

    @Override
    public List<Message> getAllMailWithWrongStatus() {
        return messageRepository.findAllByMassageStatus_Status(Status.SENDING_ERROR);
    }

    @Override
    public List<Message> allLettersWithEmailAndStatus(String email, Status status) {
        return messageRepository.findMessagesByEmailAndMassageStatus_Status(email, status);
    }

    private void sendingMessageLetter(Message message){
        try {
            mailServiceI.sendSimpleMessage(message.getEmail(), message.getSubject(), message.getContent());
            setMessageStatus(message.getId(), MessageStatus.builder()
                    .status(Status.SENT)
                    .build());
        }catch (MailSandingError sandingError){
            setMessageStatus(message.getId(), MessageStatus.builder()
                    .status(Status.SENDING_ERROR)
                    .errorMassage(sandingError.getMessage())
                    .build());
        }
    }

    @Scheduled(fixedRate = 300_000)
    private void checkAllWrongMails(){
        List<Message> unsentLetters = getAllMailWithWrongStatus();
        unsentLetters.forEach(this::sendingMessageLetter);
    }
}
