package com.springsecurity.jwtwithmongo.repositories;


import com.springsecurity.jwtwithmongo.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username);

}
