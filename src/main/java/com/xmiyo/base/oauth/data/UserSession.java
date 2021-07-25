package com.xmiyo.base.oauth.data;

import com.xmiyo.base.server.repository.model.Account;
import com.xmiyo.base.server.security.model.FacebookUser;
import com.xmiyo.base.server.security.model.GoogleUser;
import com.xmiyo.base.server.security.model.OAuthUser;
import com.xmiyo.base.server.security.model.OAuthUserType;
import com.xmiyo.base.server.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class UserSession implements Serializable {

    @Autowired
    private AccountService accountService;

    public ApplicationUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
            OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
            OAuthUserType oAuthUserType = OAuthUserType.valueOf(authenticationToken.getAuthorizedClientRegistrationId());
            OAuthUser user = null;
            ApplicationUser sessionUser = new ApplicationUser();
            switch (oAuthUserType){
                case google:
                    user = new GoogleUser(principal.getAttributes());
                    break;
                case facebook:
                    user = new FacebookUser(principal.getAttributes());
                    break;
                default:
                    break;
            }
            BeanUtils.copyProperties(user, sessionUser);
            Account account = accountService.findAccountByEmail(user.getEmail());
            sessionUser.setId(account.getId());
            return sessionUser;
        } else {
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            Account account = accountService.findAccountByEmail(principal.getUsername());
            return new ApplicationUser(account.getId(), account.getUsername(), account.getEmail(), null);
        }
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null;
    }
}
