package com.simbirsoft.sailclient.widget;

import jfxtras.labs.scene.control.window.Window;

import com.simbirsoft.sailclient.util.PropertiesReader;

abstract class WidgetFactory {
    Window widget;
    PropertiesReader propertiesReader;

    Window constructWidget() {
        propertiesReader = PropertiesReader.getInstance();
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
