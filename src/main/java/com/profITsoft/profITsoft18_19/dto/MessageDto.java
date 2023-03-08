package com.profITsoft.profITsoft18_19.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageDto {
    @NotEmpty
    private String subject;
    @NotEmpty
    private String content;
    @Email
    @NotEmpty
    private String email;
}
