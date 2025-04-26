package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

import static com.automation.utils.DriverManager.takeScreenShotAsBytes;

public class Hooks {
    @Before("@api")
    public void setUpAPI(){
        ConfigReader.initReader();
        RestAssured.baseURI = ConfigReader.getConfigValue("base.uri");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Before("@web or @android")
    public void setUp() {
        ConfigReader.initReader();
        DriverManager.createDriver();
    }

    @AfterStep("@web or @android")
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.attach(takeScreenShotAsBytes(), "image/png", "Failed Executing the following Scenario : " + scenario.getName());
        } else
            scenario.attach(takeScreenShotAsBytes(), "image/png", scenario.getName());
    }

    @After("@web or @android")
    public void cleanUp(Scenario scenario) {
        scenario.attach(takeScreenShotAsBytes(), "image/png", "Scenario Execution has been Completed");
        DriverManager.getDriver().quit();
    }
}
