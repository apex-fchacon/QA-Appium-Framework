package org.tft.conections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ADBDevices {
//    Method to get testing connected devices
    public static void main(String[] args) {
        List<String> devices = getConnectedDevices();
        for (String device : devices) {
            System.out.println("Device connected: " + device);
        }
    }

    public static List<String> getConnectedDevices() {
        List<String> devices = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device")) {
                    devices.add(line.split("\\s+")[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devices;
    }
}

