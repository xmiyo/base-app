package com.xmiyo.base.ui.views.login;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class SocialButtons extends HorizontalLayout {

    private static final String URL_GOOGLE = "/oauth2/authorization/google";
    private static final String URL_FACEBOOK = "/oauth2/authorization/facebook";

    public SocialButtons(boolean showFacebook, boolean showGoogle) {
        if (showFacebook)
            add(createButton("Sign in with Facebook", URL_FACEBOOK, this::onFacebookClick));
        if (showGoogle)
            add(createButton("Sign in with Google", URL_GOOGLE, this::onGoogleClick));
    }

    private Anchor createButton(String text, String url, ComponentEventListener<ClickEvent<Button>> onClickListener) {
        Button googleLoginButton = new Button(text, VaadinIcon.GOOGLE_PLUS.create());
        Anchor googleLoginButtonWrapper = new Anchor(url);
        googleLoginButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        googleLoginButton.addClickListener(onClickListener);
        googleLoginButtonWrapper.add(googleLoginButton);
        return googleLoginButtonWrapper;
    }

    private void onFacebookClick(ClickEvent<Button> event) {
        showNotification("Facebook login process started, please wait...");
    }

    private void onGoogleClick(ClickEvent<Button> event) {
        showNotification("Google login process started, please wait...");
    }

    private void showNotification(String s) {
        Notification notification = new Notification(s, 3000, Notification.Position.TOP_CENTER);
        notification.open();
    }
}