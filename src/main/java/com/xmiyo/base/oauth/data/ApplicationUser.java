package com.xmiyo.base.oauth.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationUser {

    private Long id;
    private String name;
    private String email;
    private String picture;

    public ApplicationUser(Long id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
