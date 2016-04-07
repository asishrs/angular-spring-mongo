package org.yagna.samples.angularspringmongo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.yagna.samples.angularspringmongo.config.MongoConfig;
import org.yagna.samples.angularspringmongo.config.WebMvcConfig;
import org.yagna.samples.angularspringmongo.model.data.User;

/**
 * Created by asish on 4/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoOperations mongoOps;

    @Test
    public void getUser(){
        Assert.assertNotNull(this.mongoOps.findOne(Query.query(Criteria.where("email").is("695018@gmail.com")), User.class));
    }
}
