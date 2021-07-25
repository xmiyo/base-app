package com.xmiyo.base.server.security.model;

import java.util.Map;

public class FacebookUser extends OAuthUser {

    public FacebookUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getPicture() {
        return "https://graph.facebook.com/{id}/picture?type=square".replace("{id}", getId());
    }

    @Override
    public OAuthUserType getType() {
        return OAuthUserType.facebook;
    }
}