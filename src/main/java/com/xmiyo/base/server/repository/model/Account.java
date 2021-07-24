package com.xmiyo.base.server.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean activated;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public Account(String username, String password, String role, AccountType accountType, boolean activated) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountType = accountType;
        this.activated = activated;
    }

    public Account(){}

}