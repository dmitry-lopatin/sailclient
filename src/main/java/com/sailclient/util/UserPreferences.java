package com.sailclient.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class UserPreferences {
    private static final String PREFERENCES_NODE_NAME = "groceryStore";
    private final Preferences userPreferences;
    private final PropertiesReader propertiesReader;

    private UserPreferences() {
        propertiesReader = PropertiesReader.createInstance();
        userPreferences = Preferences.userRoot().node(PREFERENCES_NODE_NAME);
    }

    public static UserPreferences createInstance() {
        return new UserPreferences();
    }

    public double getAddressWidgetLayoutX() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.ADDRESS_LAYOUT_X), propertiesReader.getAddressWidgetLayoutX());
    }
    public double getAddressWidgetLayoutY() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.ADDRESS_LAYOUT_Y), propertiesReader.getAddressWidgetLayoutY());
    }
    public double getAddressWidgetPrefWidth() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.ADDRESS_PREF_WIDTH), propertiesReader.getAddressWidgetPrefWidth());
    }
    public double getAddressWidgetPrefHeight() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.ADDRESS_PREF_HEIGHT), propertiesReader.getAddressWidgetPrefHeight());
    }
    public double getCategoryWidgetLayoutX() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.CATEGORY_LAYOUT_X), propertiesReader.getCategoryWidgetLayoutX());
    }
    public double getCategoryWidgetLayoutY() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.CATEGORY_LAYOUT_Y), propertiesReader.getCategoryWidgetLayoutY());
    }
    public double getCategoryWidgetPrefWidth() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.CATEGORY_PREF_WIDTH), propertiesReader.getCategoryWidgetPrefWidth());
    }
    public double getCategoryWidgetPrefHeight() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.CATEGORY_PREF_HEIGHT), propertiesReader.getCategoryWidgetPrefHeight());
    }
    public double getTotalWidgetLayoutX() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.TOTAL_LAYOUT_X), propertiesReader.getTotalWidgetLayoutX());
    }
    public double getTotalWidgetLayoutY() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.TOTAL_LAYOUT_Y), propertiesReader.getTotalWidgetLayoutY());
    }
    public double getTotalWidgetPrefWidth() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.TOTAL_PREF_WIDTH), propertiesReader.getTotalWidgetPrefWidth());
    }
    public double getTotalWidgetPrefHeight() {
        return userPreferences.getDouble(String.valueOf(UserPreferenceKey.TOTAL_PREF_HEIGHT), propertiesReader.getTotalWidgetPrefHeight());
    }

    public void saveWidgetPrefs(String widgetTitle, double layoutX, double layoutY, double prefWidth, double prefHeight) {
        try (OutputStream outputStream = new FileOutputStream(UserPreferences.PREFERENCES_NODE_NAME)) {
            userPreferences.put(widgetTitle+UserPreferenceKey.LAYOUT_X, String.valueOf(layoutX));
            userPreferences.put(widgetTitle+UserPreferenceKey.LAYOUT_Y, String.valueOf(layoutY));
            userPreferences.put(widgetTitle+UserPreferenceKey.PREF_WIDTH, String.valueOf(prefWidth));
            userPreferences.put(widgetTitle+UserPreferenceKey.PREF_HEIGHT, String.valueOf(prefHeight));

            userPreferences.exportSubtree(outputStream);
        } catch (IOException | BackingStoreException e) {
            e.printStackTrace();
        }
    }

}
