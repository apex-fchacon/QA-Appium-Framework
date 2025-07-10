package org.tft.conections;// This sample code supports Appium Java client >=9
// https://github.com/appium/java-client
//import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class Actions extends StartConnection{
    public Sequence clickRiotLogin() {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(837, 858);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        return tap;
    }


    public Sequence clickPlay() {
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(2045, 958);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        return tap;
    }

//    public Sequence clickGameMode() {
//        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//        var tapPoint = new Point(2045, 958);
//        var tap = new Sequence(finger, 1);
//        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
//                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
//        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
//        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//        return tap;
//    }
}

//public class SampleTest {
//
////    private AndroidDriver driver;
////
////    @Before
////    public void setUp() {
////        var options = new BaseOptions()
////                .amend("appium:settings", Map.ofEntries(Map.entry("record", true), Map.entry("ignoreUnimportantViews", false), Map.entry("allowInvisibleElements", true)))
////                .amend("platformName", "Android")
////                .amend("appium:platformVersion", "13")
////                .amend("appium:deviceName", "R58R83MKLLA")
////                .amend("appium:noReset", "true")
////                .amend("appium:app", "C:\\Users\\fchac\\Downloads\\TFT.apk")
////                .amend("appium:automationName", "UiAutomator2")
////                .amend("appium:ensureWebviewsHavePages", true)
////                .amend("appium:nativeWebScreenshot", true)
////                .amend("appium:newCommandTimeout", 3600)
////                .amend("appium:connectHardwareKeyboard", true);
//
////        private URL getUrl() {
////            try {
////                return new URL("http://127.0.0.1:4723");
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        driver = new AndroidDriver(this.getUrl(), options);
////    }
//
//
////
////    @Test
////    public void sampleTest() {
////        var packageName = driver.executeScript("mobile: getCurrentPackage");
////        var caps = driver.getSessionDetails();
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(2199, 67);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(751, 135);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(2199, 43);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(1338, 385);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(832, 866);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(645, 476);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(1169, 476);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(587, 322);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(616, 303);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        var el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"));
////        el2.sendKeys("fersh474");
////        var el3 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));
////        el3.sendKeys("Avengers3..");
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(2175, 804);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(2026, 982);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
////        var tapPoint = new Point(654, 664);
////        var tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
////                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        driver.perform(Arrays.asList(tap));
////    }
////
////    @After
////    public void tearDown() {
////        driver.quit();
////    }
//}
