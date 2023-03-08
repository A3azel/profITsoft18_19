package com.profITsoft.profITsoft18_19.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageDto {
    @NotEmpty(message = "This field can't be blank")
    private String subject;
    @NotEmpty(message = "This field can't be blank")
    private String content;
    @Email
    @NotEmpty(message = "This field can't be blank")
    private String email;
}
