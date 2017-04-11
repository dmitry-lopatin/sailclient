package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

public class CategoryWidgetBuilder extends WidgetBuilder {

    void setWidgetTitle() {
        widget.setTitle("Categories");
    }
    void setWidgetLayout() {
        widget.setLayoutX(465);
        widget.setLayoutY(5);
    }
    void setWidgetPrefSize() {
        widget.setPrefSize(200, 200);
    }
    void setWidgetMinSize() {
        widget.setMinSize(150, 100);
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
