package com.xmiyo.base.ui.views.helloworld;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.xmiyo.base.oauth.data.UserSession;
import com.xmiyo.base.ui.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "hello-world", layout = MainView.class)
@PageTitle("Hello World")
public class HelloWorldView extends HorizontalLayout {

    @Autowired
    UserSession userSession;

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {

    }

    @PostConstruct
    public void init() {

        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

        Div div = new Div();
        div.setText("Hello " + userSession.getUser().getName() + "! email: " + userSession.getUser().getEmail() );
        div.getElement().getStyle().set("font-size", "xx-large");

        String pictureUrl = userSession.getUser().getPicture();
        Image image = new Image(pictureUrl , "User Image");

        // Spring maps the 'logout' url so we should ignore it
        Anchor logout = new Anchor("/logout", "Logout");
        logout.getElement().setAttribute("router-ignore", true);


        setAlignItems(Alignment.CENTER);
        add(div, image, new Avatar(userSession.getUser().getName(), pictureUrl), logout);
    }

}
