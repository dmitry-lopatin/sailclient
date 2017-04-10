package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;
import jfxtras.labs.scene.control.window.Window;

public class WidgetConstructor {
    public static Window constuct(String title, double layoutX, double layoutY, double prefWidth, double prefHeight, double minWidth,
                                  double minHeight, boolean resizable, boolean movable, boolean visible) {
        Window window = new Window(title);
        window.setLayoutX(layoutX);
        window.setLayoutY(layoutY);
        window.setPrefSize(prefWidth, prefHeight);
        window.setMinSize(minWidth, minHeight);
        window.setResizableWindow(resizable);
        window.setMovable(movable);
        window.setVisible(visible);
        window.getContentPane().getChildren().add(new ListView());
        return window;
    }
}
