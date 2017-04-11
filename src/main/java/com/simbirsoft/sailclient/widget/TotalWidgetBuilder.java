package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

public class TotalWidgetBuilder extends WidgetBuilder {
    void setWidgetTitle() {
        widget.setTitle("Total");
    }
    void setWidgetLayout() {
        widget.setLayoutX(150);
        widget.setLayoutY(220);
    }
    void setWidgetPrefSize() {
        widget.setPrefSize(200, 75);
    }
    void setWidgetMinSize() {
        widget.setMinSize(150, 75);
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
