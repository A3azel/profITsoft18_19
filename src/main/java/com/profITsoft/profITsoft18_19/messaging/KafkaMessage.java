package com.profITsoft.profITsoft18_19.messaging;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class KafkaMessage {
    private String transactionId;
    @NotEmpty(message = "This field can't be blank")
    private String subject;
    @NotEmpty(message = "This field can't be blank")
    private String content;
    @Email
    @NotEmpty(message = "This field can't be blank")
    private String email;
}
