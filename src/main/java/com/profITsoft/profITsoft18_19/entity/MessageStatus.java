package com.profITsoft.profITsoft18_19.entity;

import com.profITsoft.profITsoft18_19.entity.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/*@Getter
@Setter
@Builder
public class MessageStatus {
    @Field(type = FieldType.Text)
    private Status status;
    @Field(type = FieldType.Text)
    public String errorMassage;
}*/


@Getter
@Setter
@Builder
public class MessageStatus {
    private Status status;
    private String errorMassage;
}