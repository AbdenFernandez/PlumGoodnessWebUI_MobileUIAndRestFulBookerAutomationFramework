package com.automation.utils;

import com.automation.pojo.CreateBookingRequestPojo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties props;

    public static void initReader() {
        props = new Properties();
        try {
            String env = System.getProperty("env", "dev");
            props.load(new FileInputStream("src/test/resources/config/config.properties"));
            props.load(new FileInputStream("src/test/resources/config/" + env + "_config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfigValue(String key) {
        return props.getProperty(key);
    }

    public static void setConfigValue(String key, String value) {
        props.setProperty(key, value);
    }

    public static void setConfigValue(String s, Object object) {
        props.put(s,object);
    }
}
