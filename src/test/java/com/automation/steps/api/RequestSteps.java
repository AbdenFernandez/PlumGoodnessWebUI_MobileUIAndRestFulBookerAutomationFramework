package com.automation.steps.api;

import com.automation.pojo.CreateTokenPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.lang.reflect.Field;


public class RequestSteps {


    @Given("user wants to call {string} end point")
    public void userWantsToCallEndPoint(String endPoint) {
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @And("set header {string} to {string}")
    public void setHeaderTo(String key, String value) {
        RestAssuredUtils.setHeader(key,value);
    }

    @Then("user set body from {string} file")
    public void userSetBodyFromFile(String filename) {
        RestAssuredUtils.setBody(filename);
    }

    @When("user performs a post call")
    public void userPerformsAPostCall() {
        RestAssuredUtils.post();
    }


    @Then("user set body from {string} file with updated field {string} and value {string}")
    public void userSetBodyFromFileWithUpdatedFieldAndValue(String file, String key, String value) {
        String content = RestAssuredUtils.getDataFromJson(file);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CreateTokenPojo createTokenPojo = objectMapper.readValue(content, CreateTokenPojo.class);

            Field field;
            field = CreateTokenPojo.class.getDeclaredField("username");
            field.setAccessible(true);
            field.set(createTokenPojo,key);

            field = CreateTokenPojo.class.getDeclaredField("password");
            field.setAccessible(true);
            field.set(createTokenPojo,value);

            RestAssuredUtils.setBody(createTokenPojo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @When("user set body from {string} file as {string}")
    public void userSetBodyFromFileAs(String fileName, String pojoClassName) throws Exception {
        RestAssuredUtils.setBody(fileName,pojoClassName);
    }

    @Then("user set path parameter {string} to {string} from config")
    public void userSetPathParameterToFromConfig(String path, String config) {
        RestAssuredUtils.setPathParam(path, ConfigReader.getConfigValue(config));
    }

    @When("user performs a get call")
    public void userPerformsAGetCall() {
        RestAssuredUtils.get();
    }

    @When("user performs a delete call")
    public void userPerformsADeleteCall() {
        RestAssuredUtils.delete();
    }

    @And("set cookie header {string} to {string}")
    public void setCookieHeaderTo(String key, String value) {
        RestAssuredUtils.setHeader(key,"token=" + ConfigReader.getConfigValue(value));
    }
}
