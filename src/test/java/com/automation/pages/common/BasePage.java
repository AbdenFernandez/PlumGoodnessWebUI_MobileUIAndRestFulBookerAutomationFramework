package com.automation.pages.common;

import com.automation.utils.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public abstract class BasePage {
    public WebDriver driver;
    WebDriverWait wait;

    public BasePage() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void waitForAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForElementVisible(WebElement ele) {
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementClickable(WebElement ele) {
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public String getFormattedDate(String expectedFormat, String date, String currentDateFormat) {
        try {
            SimpleDateFormat currentFormatter = new SimpleDateFormat(currentDateFormat);
            Date dateObject = currentFormatter.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateObject);
            SimpleDateFormat expectedFormatter = new SimpleDateFormat(expectedFormat);
            return expectedFormatter.format(calendar.getTime());
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format " + expectedFormat);
        }
    }

    public void pause(long milliSec) {
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            setImplicitWait(10);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        } finally {
            setImplicitWait(20);
        }
    }

    public void setImplicitWait(long sec) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    public void zoomIn(WebElement element) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            int centerX, centerY, startX1, startY1, endX1, endY1, startX2, startY2, endX2, endY2;
            centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
            centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);
            //first finger
            startX1 = centerX - 100;
            startY1 = centerY - 100;
            endX1 = startX1 - 100;
            endY1 = startY1 - 100;
            //second finger
            startX2 = centerX + 100;
            startY2 = centerY + 100;
            endX2 = startX2 + 100;
            endY2 = startY2 + 100;
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
            Sequence zoom1 = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX1, startY1))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger1.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), endX1, endY1))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            Sequence zoom2 = new Sequence(finger2, 1)
                    .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX2, startY2))
                    .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger2.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), endX2, endY2))
                    .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            androidDriver.perform(Arrays.asList(zoom1, zoom2));
        } else {
            throw new RuntimeException("The driver is not an instance of AndroidDriver");
        }
    }

    public void tap(WebElement element) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            int tapX = element.getLocation().getX() + 5; // Tap closer to the left edge
            int tapY = element.getLocation().getY() + (height / 2);

            // Debugging information
            System.out.println("Element Width: " + width);
            System.out.println("Element Height: " + height);
            System.out.println("Tap X Coordinate: " + tapX);
            System.out.println("Tap Y Coordinate: " + tapY);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tapSequence = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), tapX, tapY))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            androidDriver.perform(Collections.singletonList(tapSequence));
        } else {
            throw new RuntimeException("The driver is not an instance of AndroidDriver");
        }
    }

    public void scroll(int startX, int startY, int endX, int endY) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            androidDriver.perform(Collections.singletonList(sequence));
        } else {
            throw new RuntimeException("The driver is not an instance of AndroidDriver");
        }
    }

    public void scroll(WebElement topElement, WebElement bottomElement) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            int width = bottomElement.getSize().getWidth();
            int height = bottomElement.getSize().getHeight();
            int startX = (bottomElement.getLocation().getX()) + (width / 2);
            int startY = (bottomElement.getLocation().getY()) + (height / 2);
            width = topElement.getSize().getWidth();
            height = topElement.getSize().getHeight();
            int endX = (topElement.getLocation().getX()) + (width / 2);
            int endY = (topElement.getLocation().getY()) + (height / 2);
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger1.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY)).
                    addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            androidDriver.perform(Collections.singletonList(sequence));
        } else {
            throw new RuntimeException("The driver is not an instance of AndroidDriver");

        }
    }
}