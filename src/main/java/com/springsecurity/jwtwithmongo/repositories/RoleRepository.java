package com.springsecurity.jwtwithmongo.repositories;


import com.springsecurity.jwtwithmongo.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role,String> {

    Role findByName(String name);
}
