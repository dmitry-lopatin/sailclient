package com.simbirsoft.sailclient.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    FileInputStream fileInputStream;
    Properties properties;
    static String hostUrl;

    public void readProperties() throws IOException {
        properties = new Properties();
        fileInputStream = new FileInputStream("src/main/resources/config.properties");
        properties.load(fileInputStream);

        hostUrl = properties.getProperty("hostUrl");
    }

    public String getAllAddressesUrl() {
        return hostUrl+"getAllAddresses";
    }
    public String getAllCategoriesUrl() {
        return hostUrl+"getAllCategories";
    }
    public String getTotalByAddressesAndCategoriesUrl() {
        return hostUrl+"getSumByAddressesAndCategories";
    }
    public String getCategoriesByAddressListUrl() {
        return hostUrl+"getCategoriesByAddressList";
    }
    public static final String GET_ALL_CATEGORIES = hostUrl+"getAllCategories";
    public static final String GET_TOTAL_BY_ADDRESSES_AND_CATEGORIES = hostUrl+"getSumByAddressesAndCategories";
    public static final String GET_CATEGORIES_BY_ADDRESS_LIST = hostUrl+"getCategoriesByAddressList";
}
