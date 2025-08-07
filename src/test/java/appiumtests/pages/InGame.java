package appiumtests.pages;

import io.qameta.allure.Step;
import org.tft.Assertions;

import java.sql.SQLOutput;

public class InGame extends Common{

    Assertions asserts = new Assertions();

    @Step("Move champ from bench to board")
    public InGame champ1board(){
        try {
            Thread.sleep(10000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        moveElement(25.04,89.07,50.13,66.39,1);
        try {
            Thread.sleep(38000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        takeScreenshotinPoint(2186, 923, 66,80, "goldBeforeBuy");
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        return this;
    }

    @Step("Move champ from bench to board")
    public InGame champ2board(){
        takeScreenshotinPoint(540, 913, 140,140, "Before Try move champ");
        moveElement(25.04,89.07,62.96,66.39,1);
        takeScreenshotinPoint(540, 913, 140,140, "After Try move champ");
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        return this;
    }

    @Step("Buy champion")
    public InGame buyChamp(){
        try {
            Thread.sleep(50); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        clickElement(61.21,19.07);
        try {
            Thread.sleep(2000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        takeScreenshotinPoint(2186, 923, 66,80, "goldAfterBuy");

        return this;
    }

    @Step("Surrender the game")
    public InGame surrender(){
        clickElement(83.3, 69.2);
        clickElement(83.8, 5.6);
        clickElement(67.9, 84.6);
        clickElement(56.4, 62.2);
//        waitTextBasedOnImage();
        try {
            Thread.sleep(10000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        clickElement(49.8, 62.6);

        return this;
    }

    /**
     * waits that text is displayed in image
     */
    private void waitTextBasedOnImage(){
        takeScreenshotinPoint(1871, 918, 268,101, "playCompare");
        String value = asserts.getText("playCompare").trim();
        String expected = asserts.getText("imagen").trim();
        System.out.println("WIP_Features 1 " + value + " 2 " + expected);
        System.out.println(value==expected);
        if (value.toLowerCase() != expected.toLowerCase()) {
            System.out.println(value.toLowerCase());
            System.out.println(expected.toLowerCase());
            System.out.println("dentros"+value.toLowerCase() != expected.toLowerCase());
            waitTextBasedOnImage();
        }
    }
}
