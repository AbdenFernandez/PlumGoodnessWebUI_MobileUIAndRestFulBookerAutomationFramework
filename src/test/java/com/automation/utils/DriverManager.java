package com.automation.utils;

import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.time.Duration;

public class DriverManager {
    @Getter
    static WebDriver driver;

    public static void createDriver() {
        if (System.getProperty("platform","web").equals("web")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if (System.getProperty("platform","web").equals("mobile")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", ConfigReader.getConfigValue("platform.name"));
            capabilities.setCapability("automationName", ConfigReader.getConfigValue("automation.name"));
            capabilities.setCapability("app", System.getProperty("user.dir") + ConfigReader.getConfigValue("app.path"));
            capabilities.setCapability("deviceName", ConfigReader.getConfigValue("device.name"));
            capabilities.setCapability("appPackage", ConfigReader.getConfigValue("app.package"));
            capabilities.setCapability("appActivity", ConfigReader.getConfigValue("app.activity"));
            capabilities.setCapability("autoGrantPermissions", ConfigReader.getConfigValue("grant.permission"));
//            capabilities.setCapability("chromedriverExecutable",System.getProperty("user.dir") + ConfigReader.getConfigValue("chrome.driver"));
            driver = new AndroidDriver(capabilities);
        } else {
            throw new RuntimeException("'platform' parameter value can be web or mobile");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    public static byte[] takeScreenShotAsBytes() {
        return  ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}