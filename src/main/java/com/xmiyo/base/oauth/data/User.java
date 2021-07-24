package com.xmiyo.base.oauth.data;

import lombok.Data;

@Data
public class User {

    private String name;
    private String email;
    private String picture;

    public User(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
