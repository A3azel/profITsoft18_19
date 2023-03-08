package com.profITsoft.profITsoft18_19.repository;

import com.profITsoft.profITsoft18_19.entity.Message;
import com.profITsoft.profITsoft18_19.entity.enums.Status;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, String> {
    List<Message> findAllByMassageStatus_Status(Status status);
    Message findMessageById(String id);

}
