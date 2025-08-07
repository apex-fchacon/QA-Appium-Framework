package appiumtests.tests;

import appiumtests.pages.InGame;
import appiumtests.pages.Login;
import appiumtests.pages.Menu;
import appiumtests.pages.Validations;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.After;
import org.junit.Before;
import org.tft.Assertions;
import org.tft.conections.StartConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Base {
//    private AndroidDriver driver;
    Login loginPage;
    Menu menuPage;
    InGame inGamePage;
    Validations validation;
    Assertions asserts = new Assertions();


    @Before
    public void setUp() {
        StartConnection.getInstance().startConnection();
        StartConnection.getInstance().waitForActivity();

        loginPage = new Login();
        menuPage = new Menu();
        inGamePage = new InGame();
        validation = new Validations();
        asserts = new Assertions();


//        var options = new BaseOptions()
//                .amend("platformName", "Android")
//                .amend("appium:platformVersion", "13")
//                .amend("appium:deviceName", "R58R83MKLLA")
//                .amend("appium:app", "C:\\Users\\fchac\\Downloads\\TFT.apk")
//                .amend("appium:automationName", "UiAutomator2")
//                .amend("appium:noReset", "true")
//                .amend("appium:ensureWebviewsHavePages", true)
//                .amend("appium:nativeWebScreenshot", true)
//                .amend("appium:newCommandTimeout", 3600)
//                .amend("appium:connectHardwareKeyboard", true);
//
////        private URL getUrl() {
////            try {
////                return new URL("http://127.0.0.1:4723");
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            }
////        }
//
//        try {
//            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @After
    public void turnDown(){
        try {
            String report = StartConnection.getInstance().getReport();
            StartConnection.getInstance().deleteReportData();
            StartConnection.getInstance().createReportFile(report, "report");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        StartConnection.getInstance().removeDriver();
    }
}
