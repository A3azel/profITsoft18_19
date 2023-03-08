package com.profITsoft.profITsoft18_19.messaging;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class KafkaMessage {
    private String transactionId;
    private String subject;
    private String content;
    private String email;
}
