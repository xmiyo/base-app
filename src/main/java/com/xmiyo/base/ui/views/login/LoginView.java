package com.xmiyo.base.ui.views.login;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.xmiyo.base.ui.views.main.MainView;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Route(value = "login", layout = MainView.class)
@PageTitle("Sign in")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final String DEFAULT_CLIENT_ID = "my_app_id";
    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String facebookClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    private LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.getForm().setTitle("Sign in");
        i18n.getForm().setUsername("Email");
        i18n.setAdditionalInformation("Don't have account? try with 'user' and 'password'");
        login.setI18n(i18n);

        add(login);


    }

    @PostConstruct
    public void initView() {
        boolean showFacebook = true;
        boolean showGoogle = true;
        if (DEFAULT_CLIENT_ID.equals(facebookClientId))
            showFacebook = false;
        if (DEFAULT_CLIENT_ID.equals(googleClientId))
            showGoogle = false;
        if (showFacebook || showGoogle)
            add(new SocialButtons(showFacebook, showGoogle));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
