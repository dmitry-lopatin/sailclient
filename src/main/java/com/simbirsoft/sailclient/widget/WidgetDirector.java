package com.simbirsoft.sailclient.widget;

import jfxtras.labs.scene.control.window.Window;

public class WidgetDirector {
    private WidgetBuilder widgetBuilder;

    void setWidgetBuilder(WidgetBuilder widgetBuilder) {
        this.widgetBuilder = widgetBuilder;
    }

    Window getWidget() {
        return widgetBuilder.getWidget();
    }

    void constructWidget() {
        widgetBuilder.createNewWidget();
        widgetBuilder.setWidgetTitle();
        widgetBuilder.setWidgetLayout();
        widgetBuilder.setWidgetPrefSize();
        widgetBuilder.setWidgetMinSize();
        widgetBuilder.setWidgetResizable();
        widgetBuilder.setWidgetMovable();
        widgetBuilder.setWidgetVisible();
        widgetBuilder.setWidgetContent();
    }
}
