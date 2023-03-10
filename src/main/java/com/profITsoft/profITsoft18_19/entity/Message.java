package com.profITsoft.profITsoft18_19.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*@Getter
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
}*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("KafkaTask")
public class Message {
    @Id
    private String id;
    private String subject;
    private String content;
    private String email;
    private MessageStatus massageStatus;
}
