package com.profITsoft.profITsoft18_19.entity;

import com.profITsoft.profITsoft18_19.entity.enums.Status;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageStatus {
    @Field(type = FieldType.Text)
    private Status status;
    @Field(type = FieldType.Text)
    public String errorMassage;
}
