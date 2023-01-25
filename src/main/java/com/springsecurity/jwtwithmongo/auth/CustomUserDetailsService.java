package com.springsecurity.jwtwithmongo.auth;

import com.springsecurity.jwtwithmongo.entities.User;
import com.springsecurity.jwtwithmongo.repositories.RoleRepository;
import com.springsecurity.jwtwithmongo.repositories.UserRepository;
import com.springsecurity.jwtwithmongo.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    //This method connects to the DB to authenticate the user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // Security User for the user granted authority and another UserDetails implementation.
        return new SecurityUser(user);
    }
}
