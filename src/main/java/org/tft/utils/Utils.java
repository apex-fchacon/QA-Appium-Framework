package org.tft.utils;

import io.appium.java_client.android.AndroidDriver;
import org.tft.conections.StartConnection;

import java.io.File;

public class Utils {

    AndroidDriver driver = StartConnection.getInstance().getDriver();

    public static String getDataDir(Class c) {
        File dir = new File(System.getProperty("user.dir"));
        dir = new File(dir, "src");
        dir = new File(dir, "main");
        dir = new File(dir, "resources");

        for (String s : c.getName().split("\\.")) {
            dir = new File(dir, s);
        }

        System.out.println("Using data directory: " + dir.toString());
        return dir.toString() + File.separator;
    }

    public static String getSharedDataDir(Class c) {
        File dir = new File(System.getProperty("user.dir"));
        dir = new File(dir, "src");
        dir = new File(dir, "main");
        dir = new File(dir, "resources");
        dir = new File(dir, "screenshots");

        return dir.toString() + File.separator;
    }



    public int getCoordinatesX(double coordinate) {
        int screenSize = driver.manage().window().getSize().width;
        int resultInPx = (int) Math.round((coordinate/100) * screenSize);
        return resultInPx;
    }

    public int getCoordinatesY(double coordinate) {
        int screenSize = driver.manage().window().getSize().height;
        int resultInPy = (int) Math.round((coordinate/100) * screenSize);
        return resultInPy;
    }

}