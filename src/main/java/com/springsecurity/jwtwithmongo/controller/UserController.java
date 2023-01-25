package com.springsecurity.jwtwithmongo.controller;

import com.springsecurity.jwtwithmongo.entities.User;
import com.springsecurity.jwtwithmongo.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomUserService customUserService;

    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user){
        customUserService.addUser(user);
        return user;
    }

    @PostMapping("/getUserById")
    @PreAuthorize("hasRole('USER')")
    public User getUserById(@RequestBody String id){
        return customUserService.findByUserId(id);
    }

}
