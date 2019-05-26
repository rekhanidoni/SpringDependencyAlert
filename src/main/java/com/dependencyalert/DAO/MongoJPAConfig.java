package com.dependencyalert.DAO;

import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.dependencyalert.DAO"})
@PropertySource("classpath:mongo.properties")
public class MongoJPAConfig extends AbstractMongoConfiguration {

    @Value("${mongo.database}")
    private String database;
    @Value("${mongo.host}")
    private String host;
    @Value("${mongo.port}")
    private String port;


    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.dependencyalert.DAO";
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Override
    @Bean
    public MongoClient mongoClient() {

        MongoClient client = new MongoClient((new ServerAddress(host, Integer.parseInt(port))));
        client.setWriteConcern(WriteConcern.ACKNOWLEDGED);

        return client;
    }}
