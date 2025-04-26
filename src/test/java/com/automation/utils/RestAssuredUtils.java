package com.automation.utils;

import com.automation.pojo.CreateBookingRequestPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();
    @Getter
    static Response response;
    static String endPoint;

    public static int getStatusCode() {
        return response.getStatusCode();
    }

    public static void setEndPoint(String endPoint) {
        requestSpecification = RestAssured.given();
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value) {
        requestSpecification.header(key, value);
    }

    public static void setBody(String fileName) {
        requestSpecification.body(getDataFromJson(fileName));
    }

    public static String getResponseValue(String jPath){
        return response.jsonPath().getString(jPath);
    }

    public static void get(){
        requestSpecification.log().all();
        response = requestSpecification.get(endPoint);
        response.then().log().all();
    }

    public static void post(){
        requestSpecification.log().all();
        response = requestSpecification.post(endPoint);
        response.then().log().all();
    }
    public static void delete(){
        requestSpecification.log().all();
        response = requestSpecification.delete(endPoint);
        response.then().log().all();
    }

    public static void put(){
        requestSpecification.log().all();
        response = requestSpecification.put(endPoint);
        response.then().log().all();
    }

    public static String getDataFromJson(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(ConfigReader.getConfigValue("json.path")+fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sc.useDelimiter("//Z").next();
    }

    public static void setBody(Object pojo){
        requestSpecification.body(pojo);
    }

    public static boolean isFieldAvailable(String jsonPath){
        String value = getResponseValue(jsonPath);
        return !value.isEmpty();
    }

    public static void setBody(String fileName, String pojoClassName) throws Exception {
        String body = RestAssuredUtils.getDataFromJson(fileName);
        ObjectMapper objectMapper = new ObjectMapper();

        Class<?> pojo = Class.forName("com.automation.pojo."+pojoClassName);

        Object requestPojo = objectMapper.readValue(body, pojo);
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setConfigValue("request.pojo",requestPojo);
    }

    public static void setPathParam(String path, String configValue) {
        requestSpecification.pathParam(path,configValue);
    }

}
