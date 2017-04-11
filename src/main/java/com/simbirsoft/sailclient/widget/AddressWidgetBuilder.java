package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

public class AddressWidgetBuilder extends WidgetBuilder {

    void setWidgetTitle() {
        widget.setTitle("Addresses");
    }
    void setWidgetLayout() {
        widget.setLayoutX(150);
        widget.setLayoutY(5);
    }
    void setWidgetPrefSize() {
        widget.setPrefSize(300, 200);
    }
    void setWidgetMinSize() {
        widget.setMinSize(225, 100);
    }
    void setWidgetResizable() {
        widget.setResizableWindow(true);
    }
    void setWidgetMovable() {
        widget.setMovable(true);
    }
    void setWidgetVisible() {
        widget.setVisible(true);
    }
    void setWidgetContent() {
        widget.getContentPane().getChildren().add(new ListView());
    }
}
