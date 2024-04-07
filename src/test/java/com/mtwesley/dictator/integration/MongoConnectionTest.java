package com.mtwesley.dictator.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class MongoConnectionTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testMongoConnection() {
        String dbName = mongoTemplate.getDb().getName();
        System.out.println("Connected to MongoDB database: " + dbName);

        long count = mongoTemplate.getCollection("players").countDocuments();
        assertThat(count).isGreaterThanOrEqualTo(0);
    }
}

