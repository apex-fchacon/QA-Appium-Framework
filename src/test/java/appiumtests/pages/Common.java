package appiumtests.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tft.conections.StartConnection;

import java.time.Duration;
import java.util.Arrays;

public class Common {
    AndroidDriver driver = StartConnection.getInstance().getDriver();

    public Common(){
        PageFactory.initElements(driver, this);
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

    public Common clickElement (int x, int y){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(x, y);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
        return this;
    }

    public Common moveElement (int startX, int startY,int endX, int endY, int originTime ){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(startX, startY);
        var end = new Point (endX, endY);
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

    public Common leftSwipe (){
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(2146, 616);
        var end = new Point (1790, 616);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
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
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), //Detect starting finger pressure point
                PointerInput.Origin.viewport(), start.getX(), start.getY()));//Start the swipe action and detect the start points
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); //detect the pressure point and starts the movement
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), //Drives the movement time
                PointerInput.Origin.viewport(), end.getX(), end.getY())); //determine where is going to stop
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));//mimic the lift finger action
        driver.perform(Arrays.asList(swipe));//start the order of actions
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
}
