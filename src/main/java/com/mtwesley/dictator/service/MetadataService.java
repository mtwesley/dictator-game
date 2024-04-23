package com.mtwesley.dictator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String getCollectionName(Class<?> entityClass) {
        return mongoTemplate.getCollectionName(entityClass);
    }
}