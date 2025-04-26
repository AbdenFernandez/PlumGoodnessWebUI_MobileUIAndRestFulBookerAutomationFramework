package com.automation.steps.api;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verifyStatusCodeIs(int code) {
        Assert.assertEquals(RestAssuredUtils.getStatusCode(), code);
    }

    @And("verify response has field {string}")
    public void verifyResponseHasField(String fieldName) {
        Assert.assertTrue(RestAssuredUtils.isFieldAvailable(fieldName));
    }


    @And("save the retrieved token")
    public void saveTheRetrievedToken() {
        String token = RestAssuredUtils.getResponseValue("token");
        System.out.println(token);
        ConfigReader.setConfigValue("token",token);
    }

    @And("user saves the {string} as {string}")
    public void userSavesTheAs(String fieldName, String configName) {
        String value = RestAssuredUtils.getResponseValue(fieldName);
        System.out.println(fieldName+" "+configName+" "+value);
        ConfigReader.setConfigValue(configName,value);
        System.out.println(ConfigReader.getConfigValue(configName));
    }

    @Then("verify booking id is same")
    public void verifyBookingIdIsSame() {
        String recieved = RestAssuredUtils.getResponseValue("bookingid");
        System.out.println(ConfigReader.getConfigValue("booking.id")+"======>"+recieved);
        Assert.assertEquals(recieved,ConfigReader.getConfigValue("booking.id"));
    }

    @And("verify response has same scheme {string}")
    public void verifyResponseHasSameScheme(String pojo) throws Exception {
        Response response = RestAssuredUtils.getResponse();

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonData\\"+pojo));
    }
}
