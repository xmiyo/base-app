package com.xmiyo.base.oauth.data;

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

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
            OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
            switch (authenticationToken.getAuthorizedClientRegistrationId()){
                case "google":
                    String name = String.format("%s %s", principal.getAttribute("given_name"), principal.getAttribute("family_name"));
                    return new User(name, principal.getAttribute("email"),
                            principal.getAttribute("picture"));
                case "facebook":
                    name = principal.getAttribute("name");
                    String email = principal.getAttribute("email");
                    String url = "https://graph.facebook.com/{id}/picture?type=square".replace("{id}", principal.getAttribute("id"));
                    return new User(name, email, url);
                default:
            }
        } else {
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            return new User(principal.getUsername(), "implementMe@dot.com", null );
        }
        return null;
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null;
    }
}
