package com.xmiyo.base.ui.views.home;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.xmiyo.base.ui.views.main.MainView;

@Route(value = "welcome", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Home")
public class HomeView extends Div {

    public HomeView() {
        addClassName("home-view");
        add(new Text("Content placeholder"));
    }

}
