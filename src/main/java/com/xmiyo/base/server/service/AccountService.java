package com.xmiyo.base.server.service;


import com.xmiyo.base.server.repository.AccountRepository;
import com.xmiyo.base.server.repository.model.Account;
import com.xmiyo.base.server.repository.model.AccountRole;
import com.xmiyo.base.server.security.model.FacebookUser;
import com.xmiyo.base.server.security.model.GoogleUser;
import com.xmiyo.base.server.security.model.OAuthUser;
import com.xmiyo.base.server.security.model.OAuthUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initialize(){
        if(accountRepository.findOneByUsername("admin") == null){
            save(new Account("admin", "admin", "admin@localhost", AccountRole.ADMIN.name(), true));
        }
        if(accountRepository.findOneByUsername("user") == null){
            save(new Account("user", "password", "user@localhost", AccountRole.USER.name(),true));
        }
    }

    @Transactional
    Account save(Account user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountRepository.save(user);
    }

    public OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        Account account = null;
        OAuthUser user = null;
        OAuthUserType accountType = OAuthUserType.valueOf(userRequest.getClientRegistration().getRegistrationId());
        switch (accountType){
            case google:
                user = new GoogleUser(oAuth2User.getAttributes());
                account = accountRepository.findOneByGoogleId(user.getId());
                break;
            case facebook:
                user = new FacebookUser(oAuth2User.getAttributes());
                account = accountRepository.findOneByFacebookId(user.getId());
                break;
            default:
        }
        if (Objects.isNull(account)) {
            account = accountRepository.findOneByEmail(user.getEmail());
            if (Objects.isNull(account)) {
                account = new Account();
                account.setRole(AccountRole.USER.name());
                account.setEmail(user.getEmail());
                account.setUsername(user.getName());
            }
            if (account.getGoogleId() == null && user.getType().equals(OAuthUserType.google))
                account.setGoogleId(user.getId());
            else if (account.getFacebookId() == null && user.getType().equals(OAuthUserType.facebook))
                account.setFacebookId(user.getId());
            account.setActivated(true);
        }
        account.setLastAccessed(LocalDateTime.now());
        accountRepository.save(account);
        return oAuth2User;
    }

    public Account findAccountByEmail(String email){
        return accountRepository.findOneByEmail(email);
    }
}