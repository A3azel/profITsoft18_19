package com.profITsoft.profITsoft18_19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "massage")
public class Message {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String subject;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Text)
    private String email;
    @Field(type = FieldType.Nested, includeInParent = true)
    private MessageStatus massageStatus;
}
