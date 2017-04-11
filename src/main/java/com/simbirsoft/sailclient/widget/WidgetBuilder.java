package com.simbirsoft.sailclient.widget;

import jfxtras.labs.scene.control.window.Window;

abstract class WidgetBuilder {
    Window widget;

    Window getWidget() {
        return widget;
    }

    void createNewWidget() {
        widget = new Window();
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
