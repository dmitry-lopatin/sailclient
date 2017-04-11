package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

public class CategoryWidgetFactory extends WidgetFactory {

    @Override
    void setWidgetTitle() {
        widget.setTitle("Categories");
    }

    @Override
    void setWidgetLayout() {
        widget.setLayoutX(465);
        widget.setLayoutY(5);
    }

    @Override
    void setWidgetPrefSize() {
        widget.setPrefSize(200, 200);
    }

    @Override
    void setWidgetMinSize() {
        widget.setMinSize(150, 100);
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
