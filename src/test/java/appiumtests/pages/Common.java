package appiumtests.pages;

import com.applitools.eyes.appium.Eyes;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tft.utils.Utils;
import org.tft.conections.StartConnection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class Common {
    AndroidDriver driver = StartConnection.getInstance().getDriver();
    Utils utils = new Utils();
    Eyes eyes = StartConnection.getInstance().getEyes();


    public Common(){
        PageFactory.initElements(driver, this);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }

    public Common preLoading(WebElement opt){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(opt));
        }catch (TimeoutException te){
			preLoading(opt);
        }
        return this;
    }

    //Click to interact
    public Common clickElement (double x, double y){

        int px = utils.getCoordinatesX(x);
        int py = utils.getCoordinatesY(y);
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(px, py);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
        return this;
    }

    public Common moveElement (double startX, double startY,double endX, double endY, int originTime ){
        int startPx = utils.getCoordinatesX(startX);
        int startPy = utils.getCoordinatesY(startY);
        int endPx = utils.getCoordinatesX(endX);
        int endPy = utils.getCoordinatesY(endY);
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(startPx, startPy);
        var end = new Point (endPx, endPy);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(originTime),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        return this;
    }

    public Common moveInCircleAndThrow (int startX, int startY,int ratio, int originTime ){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(startX, startY);
        var end = new Point ((startX + ratio), (startY+ ratio));
        var swipe = new Sequence(finger, 1);
        for (int i =0; i <= 12; i++ ){
            end = new Point ((startX + ratio), (startY+ ratio));
            swipe.addAction(finger.createPointerMove(Duration.ofSeconds(originTime),
                    PointerInput.Origin.viewport(), start.getX(), start.getY()));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(50),
                    PointerInput.Origin.viewport(), end.getX(), end.getY()));
        }
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        return this;
    }

    public Common leftSwipe (){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(2146, 616);
        var end = new Point (1790, 616);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), //Detect starting finger pressure point
                PointerInput.Origin.viewport(), start.getX(), start.getY()));//Start the swipe action and detect the start points
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); //detect the pressure point and starts the movement
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300),//Drives the movement time
                PointerInput.Origin.viewport(), end.getX(), end.getY()));//determine where is going to stop
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));//mimic the lift finger action
        driver.perform(Arrays.asList(swipe));//start the order of actions
        return this;
    }

    public Common rightSwipe (){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point (1790, 616);
        var end = new Point(2146, 616);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        return this;
    }

    public Common upSwipe (){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(2146, 611);
        var end = new Point (1790, 616);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        return this;
    }

    public Common downSwipe (){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(2146, 611);
        var end = new Point (1790, 616);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        return this;
    }

    public Common takeScreenshot (String name){
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Guarda la captura en un archivo
        try {
            FileUtils.copyFile(screenshotFile, new File("src/main/resources/gameImages/"+ name +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

//    @Override
    public byte[] captureScreenshot (){
        byte screenshotBytes[] = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshotBytes;
    }

     public Common takeScreenshotinPoint (int x, int y, int width, int height, String screenshotName){
         File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

         // cut a specific area (i.e, coordinates x, y, wide & high)
         // you can use the size of your element or desired area
         // Cut the image
         BufferedImage fullImage = null;
         try {
             fullImage = ImageIO.read(screenshotFile);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
//         String screenshotName = "screenshotName.png";
         BufferedImage croppedImage = fullImage.getSubimage(x, y, width, height);

         // Guarda la captura recortada en un archivo
         String pathName = "src/main/resources/screenshots/" + screenshotName + ".png";
         File croppedFile = new File(pathName);
         try {
             ImageIO.write(croppedImage, "png", croppedFile);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

         return this;
     }

}
