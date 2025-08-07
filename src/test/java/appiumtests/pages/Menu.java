package appiumtests.pages;

import io.qameta.allure.Step;

public class Menu extends Common{
    @Step("Clicks on Play button")
    public Menu play(){
        try {
            Thread.sleep(20000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        clickElement(83.58, 89.35);
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        return this;
    }

    @Step("Selects the Game mode")
    public Menu wihGameMode(String gameMode){
        try {
            Thread.sleep(5000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        switch (gameMode.toUpperCase()) {
            case ("TOCKER'S TRIALS"):
                rightSwipe();
                clickElement(13.83, 63.70);
                break;
            case ("NORMAL"):
                rightSwipe();
                clickElement(31.08, 63.70);
                break;
            case ("RANKED"):
                rightSwipe();
                clickElement(48.71, 63.70);
                break;
            case ("DOUBLE UP"):
                rightSwipe();
                clickElement(66.54, 63.70);
                break;
            case ("HYPER ROLL"):
                rightSwipe();
                clickElement(84.42, 63.70);
                break;
            case ("TUTORIAL"):
                leftSwipe();
                clickElement(91.42, 63.70);
                acceptTutorial();
                break;
        }
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        return this;
    }

    @Step("Starts to search a new game")
    public Menu startGame(){
        try {
            Thread.sleep(5000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        clickElement(84.21, 89.54);
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        return this;
    }

    @Step("Accept the game found")
    public Menu acceptGame(){
        try {
            Thread.sleep(5000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        clickElement(49.92, 75.74);
        return this;
    }

    @Step("Accept tutorial")
    private void acceptTutorial(){
        byte[] screenshot = captureScreenshot();
        attachScreenshot(screenshot);
        clickElement(56.21, 62.22);
        try {
            Thread.sleep(30000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }
    }
}
