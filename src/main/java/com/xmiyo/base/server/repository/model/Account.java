package com.xmiyo.base.server.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String googleId;
    private String facebookId;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean activated;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessed;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Account(String username, String password, String role, boolean activated) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.activated = activated;
    }

    public Account(){}

}