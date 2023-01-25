package com.springsecurity.jwtwithmongo.security;


import com.springsecurity.jwtwithmongo.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> roles = new ArrayList<>();
        List<String> userRoles = Arrays.stream(user.getRole().split(",")).toList();

        for (String role : userRoles) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return roles;

    /*
    User ın rol tipi List<Role> roles şeklindeyken
          List<GrantedAuthority> roles = new ArrayList<>();
        for(Role role:user.getRoles()){
            roles.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return roles;*/
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
