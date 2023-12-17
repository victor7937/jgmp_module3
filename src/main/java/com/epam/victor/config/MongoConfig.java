package com.epam.victor.config;

import com.epam.victor.model.converter.CurrencyPairReadingConverter;
import com.epam.victor.model.converter.CurrencyPairWritingConverter;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@Profile("mongo")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.epam.victor.repository");
    }


    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(String.format(
                "mongodb://localhost:%s/%s",
                env.getProperty("spring.data.mongodb.host"),
                env.getProperty("spring.data.mongodb.database")));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected void configureConverters(MongoCustomConversions.MongoConverterConfigurationAdapter adapter) {
        adapter.registerConverter(new CurrencyPairWritingConverter());
        adapter.registerConverter(new CurrencyPairReadingConverter());
    }

}
