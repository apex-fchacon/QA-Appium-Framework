package appiumtests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

//MobileElement username = driver.findElementByXPath("//android.view.View[@resource-id=\"root\"]/android.view.View[2]/android.view.View[1]/android.view.View/android.widget.EditText");
//        MobileElement password = driver.findElementByXPath("//android.view.View[@resource-id=\"root\"]/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.EditText");
//        MobileElement signin = driver.findElementByXPath("//android.widget.Button[@text=\"Sign in\"]");
//        username.sendKeys("fersh474");
//        password.sendKeys("Avengers3..");
//        signin.click();
public class Login extends Common {
//    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"com.riotgames.league.teamfighttactics:id/mobilefre_webviewModal\"]/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText")
//    private WebElement txtUsername;
//
//    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id=\"com.riotgames.league.teamfighttactics:id/mobilefre_webviewModal\"]/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")
//    private WebElement txtPassword;
//
//    @AndroidFindBy(xpath = "//android.widget.Button[@text=\"Iniciar sesión\"]")
//    private WebElement btnSigin;



    public Login loginWith(String loginType){
        switch (loginType) {
            case ("Riot"):
                clickElement(852, 852);
                break;
            case ("Google"):
                clickElement(1083, 852);
                break;
            case ("Xbox"):
                clickElement(1314, 852);
                break;
            case ("Facebook"):
                clickElement(1545, 852);
                break;
        }
        return this;
    }

    public Login loginAs(String userName){
        try {
            Thread.sleep(10000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }

        WebElement txtUsername = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        try {
            txtUsername.clear();
        }catch (TimeoutException te){
            preLoading(txtUsername);
            txtUsername.clear();
        }
        txtUsername.sendKeys(userName);
        return this;
    }

    public Login withPassword(String userName){
        try {
            Thread.sleep(5000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }

        WebElement txtPassword = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        try {
            txtPassword.clear();
        }catch (TimeoutException te){
            preLoading(txtPassword);
            txtPassword.clear();
        }
        txtPassword.sendKeys(userName);
        return this;
    }

    public Login logIn(){
        try {
            Thread.sleep(2000); // Pausa durante 1000 milisegundos (1 segundo)
        } catch (InterruptedException e) {
            // Manejo de excepciones aquí
        }

        WebElement btnSigIn = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)"));
//        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(txtUsername));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        thread.sleep(30000);
        try {
            btnSigIn.clear();
        }catch (TimeoutException te){
            preLoading(btnSigIn);
            btnSigIn.clear();
        }
        btnSigIn.click();
        return this;
    }
}
