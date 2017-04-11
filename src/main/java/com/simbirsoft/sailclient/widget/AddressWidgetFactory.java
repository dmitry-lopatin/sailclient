package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

public class AddressWidgetFactory extends WidgetFactory {

    @Override
    void setWidgetTitle() {
        widget.setTitle("Addresses");
    }

    @Override
    void setWidgetLayout() {
        widget.setLayoutX(150);
        widget.setLayoutY(5);
    }

    @Override
    void setWidgetPrefSize() {
        widget.setPrefSize(300, 200);
    }

    @Override
    void setWidgetMinSize() {
        widget.setMinSize(225, 100);
    }

    @Override
    void setWidgetResizable() {
        widget.setResizableWindow(true);
    }

    @Override
    void setWidgetMovable() {
        widget.setMovable(true);
    }

    @Override
    void setWidgetVisible() {
        widget.setVisible(true);
    }

    @Override
    void setWidgetContent() {
        widget.getContentPane().getChildren().add(new ListView());
    }
}
