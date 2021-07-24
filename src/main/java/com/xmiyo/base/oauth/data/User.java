package com.xmiyo.base.oauth.data;

import lombok.Data;

@Data
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String picture;

    public User(String firstName, String lastName, String email, String picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
    }
}
