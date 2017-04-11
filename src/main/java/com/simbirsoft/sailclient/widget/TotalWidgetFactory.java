package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

public class TotalWidgetFactory extends WidgetFactory {

    @Override
    void setWidgetTitle() {
        widget.setTitle("Total");
    }

    @Override
    void setWidgetLayout() {
        widget.setLayoutX(150);
        widget.setLayoutY(220);
    }

    @Override
    void setWidgetPrefSize() {
        widget.setPrefSize(200, 75);
    }

    @Override
    void setWidgetMinSize() {
        widget.setMinSize(150, 75);
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
