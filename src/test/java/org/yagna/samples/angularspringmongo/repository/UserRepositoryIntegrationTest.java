package org.yagna.samples.angularspringmongo.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

import java.util.UUID;

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

    private final String email = UUID.randomUUID() + "@test.com";

    final User user = new User();

    @Before
    public void testSetup() {
        user.setEmail(this.email);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPassword(UUID.randomUUID().toString());
        this.userRepository.insert(user);
    }

    @After
    public void tearDown() {
        this.userRepository.delete(user);
    }

    @Test
    public void getUser(){
        User persistedUser = this.mongoOps.findOne(Query.query(Criteria.where("email").is(this.email)), User.class);
        Assert.assertNotNull(persistedUser);
        Assert.assertEquals(persistedUser.getEmail(), this.email);
        Assert.assertNotNull(persistedUser.getFirstName());
        Assert.assertNotNull(persistedUser.getLastName());
        Assert.assertNotNull(persistedUser.getPassword());
    }
}
