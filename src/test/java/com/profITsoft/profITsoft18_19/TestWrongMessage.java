package com.profITsoft.profITsoft18_19;

import com.profITsoft.profITsoft18_19.entity.enums.Status;
import com.profITsoft.profITsoft18_19.messaging.KafkaMessage;
import com.profITsoft.profITsoft18_19.service.serviceInterface.MailService;
import com.profITsoft.profITsoft18_19.service.serviceInterface.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestPropertySource(properties = {"spring.mail.password=password"})
public class TestWrongMessage {

    public static final String DEFAULT_EMAIL = "a943579@gmail.com";

    @Value("${kafka.topic.paymentReceived}")
    private String messageTopic;

    @Autowired
    private KafkaOperations<String, KafkaMessage> kafkaOperations;

    @SpyBean
    private MessageService messageService;

    @Test
    public void testSuccessSending(){
        KafkaMessage kafkaMessage = KafkaMessage.builder()
                .subject("Test mail")
                .content("Bla, bla, bla...")
                .email(DEFAULT_EMAIL)
                .build();

        kafkaOperations.send(messageTopic, kafkaMessage);

        int oldLettersCount = messageService.allLettersWithEmailAndStatus(DEFAULT_EMAIL, Status.SENT).size();

        verify(messageService, after(5000)).messageProcess(any());
        int newLettersCount = messageService.allLettersWithEmailAndStatus(DEFAULT_EMAIL, Status.SENT).size();
        Assertions.assertEquals(oldLettersCount, newLettersCount-1);

    }

    @Test
    public void testErrorSending(){
        KafkaMessage kafkaMessage = KafkaMessage.builder()
                .subject("Test mail")
                .content("Bla, bla, bla...")
                .email(DEFAULT_EMAIL)
                .build();
        kafkaOperations.send(messageTopic, kafkaMessage);

        int oldLettersCount = messageService.allLettersWithEmailAndStatus(DEFAULT_EMAIL, Status.SENDING_ERROR).size();

        verify(messageService, after(5000)).messageProcess(any());

        int newLettersCount = messageService.allLettersWithEmailAndStatus(DEFAULT_EMAIL, Status.SENDING_ERROR).size();

        Assertions.assertEquals(oldLettersCount, newLettersCount-1);
    }
}
