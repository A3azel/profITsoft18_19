package com.profITsoft.profITsoft18_19.repository;

import com.profITsoft.profITsoft18_19.entity.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, String> {
}
