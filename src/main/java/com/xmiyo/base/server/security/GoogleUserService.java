package com.xmiyo.base.server.security;

import com.xmiyo.base.server.repository.AccountRepository;
import com.xmiyo.base.server.repository.model.Account;
import com.xmiyo.base.server.repository.model.AccountRole;
import com.xmiyo.base.server.security.model.GoogleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class GoogleUserService extends OidcUserService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
             return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

     private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUser googleUser = new GoogleUser(oidcUser.getAttributes());
         Account account = accountRepository.findOneByGoogleId(googleUser.getId());
         if (Objects.isNull(account)) {
             account = accountRepository.findOneByEmail(googleUser.getEmail());
             if (Objects.isNull(account)) {
                 account = new Account();
                 account.setRole(AccountRole.USER.name());
                 account.setEmail(googleUser.getEmail());
                 account.setUsername(googleUser.getName());
             } else {
                 account.setGoogleId(googleUser.getId());
             }
             account.setActivated(true);
         }
         account.setLastAccessed(LocalDateTime.now());
         accountRepository.save(account);
        return oidcUser;
    }
}