package com.xmiyo.base.ui.views.signin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.xmiyo.base.ui.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "login", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Sign in")
public class SigninView extends Div {

    public SigninView() {
        addClassName("signin-view");
        add(new Text("Content placeholder"));
    }

}
