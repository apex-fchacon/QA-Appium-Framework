package appiumtests.tests;

import appiumtests.pages.InGame;
import appiumtests.pages.Login;
import appiumtests.pages.Menu;
import org.junit.After;
import org.junit.Before;
import org.tft.conections.StartConnection;

public class Base {
    Login loginPage;
    Menu menuPage;
    InGame inGamePage;
    @Before
    public void setUp() {
        StartConnection.getInstance().startConnection();
        loginPage = new Login();
        menuPage = new Menu();
    }

    @After
    public void turnDown(){
        StartConnection.getInstance().removeDriver();
    }
}
