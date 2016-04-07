package org.yagna.samples.angularspringmongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asish on 4/6/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = "org.yagna.samples.angularspringmongo.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.db.name}")
    private String databaseName;

    @Value("${mongo.db.user.name}")
    private String username;

    @Value("${mongo.db.password}")
    private String password;

    @Value("${mongo.db.host}")
    private String host;

    @Value("${mongo.db.port}")
    private String port;

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(this.username, this.databaseName, this.password.toCharArray());
        List<MongoCredential> mongoCredentials = new ArrayList<MongoCredential>(1);
        mongoCredentials.add(mongoCredential);
        ServerAddress serverAddress = new ServerAddress(this.host, NumberUtils.toInt(this.port));
        return new MongoClient(serverAddress, mongoCredentials);
    }

    @Override
    public String getMappingBasePackage() {
        return "org.yagna.samples.angularspringmongo";
    }

}
