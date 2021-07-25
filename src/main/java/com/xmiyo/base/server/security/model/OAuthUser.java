package com.xmiyo.base.server.security.model;

import java.util.Map;

public abstract class OAuthUser {

    protected Map<String, Object> attributes;

    public abstract String getId();

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public abstract String getPicture();

    public abstract OAuthUserType getType();
}