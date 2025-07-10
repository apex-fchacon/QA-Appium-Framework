package org.tft.conections;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import java.net.URL;

public class StartConnection {
    Map<String, String> defaults = new HashMap<String, String>() {{
//        put("deviceName","Galaxy A32");
        put("deviceName", "R58R83MKLLA");
        put("platformVersion", "13");
    }};


    private AndroidDriver driver;

    private static StartConnection instance = new StartConnection();

    public static StartConnection getInstance(){
        return instance;
    }

    public AndroidDriver getDriver(){
        return driver;
    }

    public void startConnection(){
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:platformVersion", defaults.get("platformVersion"))
                .amend("appium:deviceName", defaults.get("platformVersion"))
                .amend("appPackage", "com.riotgames.league.teamfighttactics")
                .amend("appActivity", "com.riotgames.leagueoflegends.RiotNativeActivity")
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:noReset", "true")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Service Started . . . .");
    }

    public void removeDriver(){
        if(driver.getSessionId() != null){
            try {
                driver.quit();
                System.out.println("Service Stopped . . . .");
            }catch (Exception e){
                System.err.println("Unable to quit");
            }
        }
    }
}
