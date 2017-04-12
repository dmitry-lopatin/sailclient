package com.sailclient.widget;

import jfxtras.labs.scene.control.window.Window;

import com.sailclient.util.PropertiesReader;
import com.sailclient.util.UserPreferences;

abstract class WidgetFactory {
    Window widget;
    PropertiesReader propertiesReader;
    UserPreferences userPreferences;

    Window constructWidget() {
        propertiesReader = PropertiesReader.createInstance();
        userPreferences = UserPreferences.createInstance();
        widget = new Window();
        setWidgetTitle();
        setWidgetLayout();
        setWidgetPrefSize();
        setWidgetMinSize();
        setWidgetResizable();
        setWidgetMovable();
        setWidgetVisible();
        setWidgetContent();
        return widget;
    }

    abstract void setWidgetTitle();
    abstract void setWidgetLayout();
    abstract void setWidgetPrefSize();
    abstract void setWidgetMinSize();
    abstract void setWidgetResizable();
    abstract void setWidgetMovable();
    abstract void setWidgetVisible();
    abstract void setWidgetContent();
}
