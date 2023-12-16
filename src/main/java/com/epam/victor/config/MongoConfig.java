package com.epam.victor.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("mongo")
@EnableMongoRepositories(basePackages = "com.epam.victor.repository")
public class MongoConfig {

    @Autowired
    private Environment env;

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(String.format(
                "mongodb://localhost:%s/%s",
                env.getProperty("spring.data.mongodb.host"),
                env.getProperty("spring.data.mongodb.database")));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(),  env.getProperty("spring.data.mongodb.database"));
    }
}
