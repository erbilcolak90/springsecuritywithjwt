package com.springsecurity.jwtwithmongo.services;

import com.springsecurity.jwtwithmongo.auth.CustomUserDetailsService;
import com.springsecurity.jwtwithmongo.entities.User;
import com.springsecurity.jwtwithmongo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User addUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByUserId(String userId){
        return userRepository.findById(userId).orElseThrow();
    }

}
