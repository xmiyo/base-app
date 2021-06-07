package com.xmiyo.base.views.main;

import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.xmiyo.base.views.main.MainView;
import com.xmiyo.base.views.signin.SigninView;
import com.xmiyo.base.views.register.RegisterView;
import com.xmiyo.base.views.home.HomeView;
import com.xmiyo.base.views.helloworld.HelloWorldView;
import com.xmiyo.base.views.cardlist.CardListView;
import com.xmiyo.base.views.masterdetail.MasterDetailView;
import com.xmiyo.base.views.collaborativemasterdetail.CollaborativeMasterDetailView;
import com.xmiyo.base.views.personform.PersonFormView;
import com.xmiyo.base.views.addressform.AddressFormView;
import com.xmiyo.base.views.creditcardform.CreditCardFormView;
import com.xmiyo.base.views.map.MapView;
import com.xmiyo.base.views.editor.EditorView;
import com.xmiyo.base.views.imagelist.ImageListView;
import com.vaadin.flow.component.page.Push;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "Base App", shortName = "Base App", enableInstallPrompt = false)
@Push
@Theme(themeFolder = "baseapp")
public class MainView extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle;

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setClassName("sidemenu-header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);
        layout.add(new Avatar());
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setClassName("sidemenu-menu");
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "Base App logo"));
        logoLayout.add(new H1("Base App"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[]{createTab("Sign in", SigninView.class), createTab("Register", RegisterView.class),
                createTab("Home", HomeView.class), createTab("Hello World", HelloWorldView.class),
                createTab("Card List", CardListView.class), createTab("Master-Detail", MasterDetailView.class),
                createTab("Collaborative Master-Detail", CollaborativeMasterDetailView.class),
                createTab("Person Form", PersonFormView.class), createTab("Address Form", AddressFormView.class),
                createTab("Credit Card Form", CreditCardFormView.class), createTab("Map", MapView.class),
                createTab("Editor", EditorView.class), createTab("Image List", ImageListView.class)};
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
