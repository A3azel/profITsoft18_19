package com.profITsoft.profITsoft18_19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "massage")
public class Message {
    @Id
    private String id;
    private String subject;
    private String content;
    private String email;
    private MessageStatus massageStatus;
}
