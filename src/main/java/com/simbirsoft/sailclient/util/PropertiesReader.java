package com.simbirsoft.sailclient.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private FileInputStream fileInputStream;
    private Properties properties;
    private String getAllAddressesUrl;
    private String getAllCategoriesUrl;
    private String getCategoriesByAddressListUrl;
    private String getTotalByAddressesAndCategoriesUrl;
    private String connectionFailureMessage;

    public void readProperties() throws IOException {
        properties = new Properties();
        fileInputStream = new FileInputStream("src/main/resources/config.properties");
        properties.load(fileInputStream);

        getAllAddressesUrl = properties.getProperty("getAllAddressesUrl");
        getAllCategoriesUrl = properties.getProperty("getAllCategoriesUrl");
        getCategoriesByAddressListUrl = properties.getProperty("getCategoriesByAddressListUrl");
        getTotalByAddressesAndCategoriesUrl = properties.getProperty("getTotalByAddressesAndCategoriesUrl");
        connectionFailureMessage = properties.getProperty("connectionFailureMessage");
    }

    public String getAllAddressesUrl() {
        return getAllAddressesUrl;
    }
    public String getAllCategoriesUrl() {
        return getAllCategoriesUrl;
    }
    public String getTotalByAddressesAndCategoriesUrl() {
        return getTotalByAddressesAndCategoriesUrl;
    }
    public String getCategoriesByAddressListUrl() {
        return getCategoriesByAddressListUrl;
    }
    public String getConnectionFailureMessage() {
        return connectionFailureMessage;
    }
}
