package com.mtwesley.dictator.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MongoConnectionTest {

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testMongoConnection() {
        assertThat(mongoTemplate.getDb().getName()).isEqualTo(dbName);
    }
}

