package com.xmiyo.base.server.security.model;

import java.util.Map;

public class GoogleUser extends OAuthUser {

    public GoogleUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return (String) attributes.get("sub");
    }

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getFirstName() {
        return (String) attributes.get("given_name");
    }

    public String getLastName() {
        return (String) attributes.get("family_name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getPicture() {
        return (String) attributes.get("picture");
    }

    @Override
    public OAuthUserType getType() {
        return OAuthUserType.google;
    }

}