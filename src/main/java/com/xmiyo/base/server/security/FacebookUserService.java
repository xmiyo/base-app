package com.xmiyo.base.server.security;

import com.xmiyo.base.server.repository.AccountRepository;
import com.xmiyo.base.server.repository.model.Account;
import com.xmiyo.base.server.repository.model.AccountRole;
import com.xmiyo.base.server.security.model.FacebookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class FacebookUserService extends DefaultOAuth2UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        FacebookUser facebookUser = new FacebookUser(oAuth2User.getAttributes());
        Account account = accountRepository.findOneByFacebookId(facebookUser.getId());
        if (Objects.isNull(account)) {
            account = accountRepository.findOneByEmail(facebookUser.getEmail());
            if (Objects.isNull(account)) {
                account = new Account();
                account.setRole(AccountRole.USER.name());
                account.setEmail(facebookUser.getEmail());
                account.setUsername(facebookUser.getName());
            } else {
                account.setFacebookId(facebookUser.getId());
            }
            account.setActivated(true);
        }
        account.setLastAccessed(LocalDateTime.now());
        accountRepository.save(account);
        return oAuth2User;
    }


}