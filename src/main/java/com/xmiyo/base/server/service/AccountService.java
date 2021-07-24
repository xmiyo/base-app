package com.xmiyo.base.server.service;


import com.xmiyo.base.server.repository.AccountRepository;
import com.xmiyo.base.server.repository.model.Account;
import com.xmiyo.base.server.repository.model.AccountRole;
import com.xmiyo.base.server.repository.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initialize(){
        if(accountRepository.findOneByUsername("admin") == null){
            save(new Account("admin", "admin", AccountRole.ADMIN.name(), AccountType.APPLICATION, true));
        }
        if(accountRepository.findOneByUsername("user") == null){
            save(new Account("user", "password", AccountRole.USER.name(), AccountType.APPLICATION, true));
        }
    }

    @Transactional
    Account save(Account user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountRepository.save(user);
    }
}