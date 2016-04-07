package org.yagna.samples.angularspringmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.yagna.samples.angularspringmongo.model.data.User;

import java.util.List;

/**
 * Created by asish on 4/6/16.
 */
public interface UserRepository extends MongoRepository<User, String> {

}
