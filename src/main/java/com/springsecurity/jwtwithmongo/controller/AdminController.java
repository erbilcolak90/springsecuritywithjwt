package com.springsecurity.jwtwithmongo.controller;


import com.springsecurity.jwtwithmongo.entities.User;
import com.springsecurity.jwtwithmongo.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomUserService customUserService;

    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("hello Jwt");
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        customUserService.addUser(user);
        return user;
    }
}
