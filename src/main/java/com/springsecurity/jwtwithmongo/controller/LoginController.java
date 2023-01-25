package com.springsecurity.jwtwithmongo.controller;

import com.springsecurity.jwtwithmongo.auth.TokenManager;
import com.springsecurity.jwtwithmongo.inputs.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

            return ResponseEntity.ok(tokenManager.generateToken(loginRequest.getUsername()));
        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        return ResponseEntity.ok(tokenManager.logoutToken(token));
    }

}
