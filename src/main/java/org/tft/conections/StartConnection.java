package org.tft.conections;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;

import org.junit.runner.Description;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.net.URL;
import java.util.UUID;

public class StartConnection {

    ADBDevices adbDevice = new ADBDevices();

    List<String> devices = adbDevice.getConnectedDevices();
    String deviceName = devices.getFirst();
    Map<String, String> defaults = new HashMap<String, String>() {{
        put("deviceName", deviceName);
        put("platformVersion", "13");
    }};


    private AndroidDriver driver;
    public static final String HTML_REPORT_DIR = System.getProperty("user.dir")+ "/src/main/resources";


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
                .amend("appium:deviceName", defaults.get("deviceName"))
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

    public void waitForActivity(){
        // Esperar hasta que la actividad principal esté activa
        String currentActivity = driver.currentActivity();
        while (!currentActivity.equals("com.riotgames.leagueoflegends.RiotNativeActivity")) {
            currentActivity = driver.currentActivity();
        }

        System.out.println("The app was launched successfully");
    }

    public void removeDriver(){

        if(driver != null && driver.getSessionId() != null) {
            driver.quit();

            System.out.println("Driver quit successfully");
        }
        driver = null;
    }


    public String getSessionId(AndroidDriver driver){
        String sessionId;
        try {
            sessionId = driver.getSessionId().toString();
        } catch (Exception e){
            sessionId = UUID.randomUUID().toString();
        }
        return sessionId;
    }

    protected void skipped(org.junit.AssumptionViolatedException e, Description description) {
        setTestInfo(null, description.getDisplayName(), "PENDING", null);
        removeDriver();
    }

    protected void succeeded(Description description) {
        setTestInfo(getSessionId(driver), description.getDisplayName(), "PASSED", null);
        removeDriver();
    }


    protected void failed(Throwable e, Description description) {
        setTestInfo(getSessionId(driver), description.getDisplayName(), "FAILED", e.getMessage().replace("\n", "\\n"));
        removeDriver();
    }

    public static void setSkippedTestInfo(String testName, String testStatus, String error) {
        try {
            String url = "http://localhost:4723/setTestInfo";
            String body = "{" +
                    "\"testName\":\""+testName+"\"," +
                    "\"testStatus\":\""+testStatus+"\"," +
                    "\"error\":\""+error+"\"" +
                    "}";
            System.out.println("url = " + url);
            System.out.println("Body of setTestInfo = " + body);
            HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .body(body).asJson();
        } catch (Exception e){
            System.out.println("Failed to set Test info");
        }
    }

    public static void setTestInfo(String sessionId, String testName, String testStatus, String error) {
        try {
            String url = "http://localhost:4723/setTestInfo";
            String body = "{" +
                    "\"sessionId\":\""+sessionId+"\"," +
                    "\"testName\":\""+testName+"\"," +
                    "\"testStatus\":\""+testStatus+"\"," +
                    "\"error\":\""+error+"\"" +
                    "}";
            System.out.println("url = " + url);
            System.out.println("Body of setTestInfo = " + body);
            HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .body(body).asJson();
        } catch (Exception e){
            System.out.println("Failed to set Test info");
        }

    }

    public String getReport() throws IOException, InterruptedException {
        String url = "http://localhost:4723/getReport";
        String s = Unirest.get(url).asString().getBody();
        return s;
    }

    public void deleteReportData() throws IOException, InterruptedException {
        String url = "http://localhost:4723/deleteReportData";
        Unirest.delete(url).asEmpty();
    }

    public void createReportFile(String data, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(HTML_REPORT_DIR + "/" + fileName + ".html");
        fileWriter.write(data);
        fileWriter.close();
    }

}
