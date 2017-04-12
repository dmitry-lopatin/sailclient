package com.simbirsoft.sailclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private final Properties properties;
    private String getAllAddressesUrl;
    private String getAllCategoriesUrl;
    private String getCategoriesByAddressListUrl;
    private String getTotalByAddressesAndCategoriesUrl;
    private String connectionFailureMessage;
    private String addressListParam;
    private String categoriesListParam;

    private PropertiesReader() {
        properties = new Properties();
        try(InputStream fileInputStream = (getClass().getResourceAsStream("/config.properties"))) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesReader createInstance() {
        return new PropertiesReader();
    }

    public void readUrlProperties() {
        getAllAddressesUrl = properties.getProperty("getAllAddressesUrl");
        getAllCategoriesUrl = properties.getProperty("getAllCategoriesUrl");
        getCategoriesByAddressListUrl = properties.getProperty("getCategoriesByAddressListUrl");
        getTotalByAddressesAndCategoriesUrl = properties.getProperty("getTotalByAddressesAndCategoriesUrl");
        connectionFailureMessage = properties.getProperty("connectionFailureMessage");
        addressListParam = properties.getProperty("addressList");
        categoriesListParam = properties.getProperty("categoryList");
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
    public String getAddressListParam() {
        return addressListParam;
    }
    public String getCategoriesListParam() {
        return categoriesListParam;
    }

    public String getAddressWidgetTitle() {
        return properties.getProperty("addressWidgetTitle");
    }
    public Double getAddressWidgetLayoutX() {
        return Double.parseDouble(properties.getProperty("addressWidgetLayoutX"));
    }
    public Double getAddressWidgetLayoutY() {
        return Double.parseDouble(properties.getProperty("addressWidgetLayoutY"));
    }
    public Double getAddressWidgetPrefWidth() {
        return Double.parseDouble(properties.getProperty("addressWidgetPrefWidth"));
    }
    public Double getAddressWidgetPrefHeight() {
        return Double.parseDouble(properties.getProperty("addressWidgetPrefHeight"));
    }
    public Double getAddressWidgetMinWidth() {
        return Double.parseDouble(properties.getProperty("addressWidgetMinWidth"));
    }
    public Double getAddressWidgetMinHeight() {
        return Double.parseDouble(properties.getProperty("addressWidgetMinHeight"));
    }

    public String getCategoryWidgetTitle() {
        return properties.getProperty("categoryWidgetTitle");
    }
    public Double getCategoryWidgetLayoutX() {
        return Double.parseDouble(properties.getProperty("categoryWidgetLayoutX"));
    }
    public Double getCategoryWidgetLayoutY() {
        return Double.parseDouble(properties.getProperty("categoryWidgetLayoutY"));
    }
    public Double getCategoryWidgetPrefWidth() {
        return Double.parseDouble(properties.getProperty("categoryWidgetPrefWidth"));
    }
    public Double getCategoryWidgetPrefHeight() {
        return Double.parseDouble(properties.getProperty("categoryWidgetPrefHeight"));
    }
    public Double getCategoryWidgetMinWidth() {
        return Double.parseDouble(properties.getProperty("categoryWidgetMinWidth"));
    }
    public Double getCategoryWidgetMinHeight() {
        return Double.parseDouble(properties.getProperty("categoryWidgetMinHeight"));
    }

    public String getTotalWidgetTitle() {
        return properties.getProperty("totalWidgetTitle");
    }
    public Double getTotalWidgetLayoutX() {
        return Double.parseDouble(properties.getProperty("totalWidgetLayoutX"));
    }
    public Double getTotalWidgetLayoutY() {
        return Double.parseDouble(properties.getProperty("totalWidgetLayoutY"));
    }
    public Double getTotalWidgetPrefWidth() {
        return Double.parseDouble(properties.getProperty("totalWidgetPrefWidth"));
    }
    public Double getTotalWidgetPrefHeight() {
        return Double.parseDouble(properties.getProperty("totalWidgetPrefHeight"));
    }
    public Double getTotalWidgetMinWidth() {
        return Double.parseDouble(properties.getProperty("totalWidgetMinWidth"));
    }
    public Double getTotalWidgetMinHeight() {
        return Double.parseDouble(properties.getProperty("totalWidgetMinHeight"));
    }
}
