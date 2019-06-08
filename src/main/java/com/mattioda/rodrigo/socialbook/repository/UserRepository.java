package com.mattioda.rodrigo.socialbook.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mattioda.rodrigo.socialbook.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
