package com.xmiyo.base.server.security.model;

import java.util.Map;

public class FacebookUser {

    private Map<String, Object> attributes;

    public FacebookUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return (String) attributes.get("id");
    }

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }
}