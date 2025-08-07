package appiumtests;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.tft.conections.StartConnection;

import java.io.IOException;

public class hooks {

    @BeforeAll
    public static void setUp() {
        StartConnection.getInstance().startConnection();
        StartConnection.getInstance().waitForActivity();
    }

    @AfterAll
    public static void turnDown(){
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
        System.out.println("Quit successful");
    }
}
