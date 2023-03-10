package com.profITsoft.profITsoft18_19.repository;

import com.profITsoft.profITsoft18_19.entity.Message;
import com.profITsoft.profITsoft18_19.entity.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoTestRepo extends MongoRepository<Message, String> {
    List<Message> findAllByMassageStatus_Status(Status status);
    Message findMessageById(String id);
    List<Message> findMessagesByEmailAndMassageStatus_Status(String email, Status status);
}
