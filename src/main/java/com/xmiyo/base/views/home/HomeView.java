package com.xmiyo.base.views.home;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.xmiyo.base.views.main.MainView;

@Route(value = "welcome", layout = MainView.class)
@PageTitle("Home")
public class HomeView extends Div {

    public HomeView() {
        addClassName("home-view");
        add(new Text("Content placeholder"));
    }

}
