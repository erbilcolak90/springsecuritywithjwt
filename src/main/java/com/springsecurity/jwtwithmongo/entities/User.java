package com.springsecurity.jwtwithmongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Users")
public class User {

    @Id
    private String id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    //private List<Role> roles;
    private String role;
/*    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;*/



}
