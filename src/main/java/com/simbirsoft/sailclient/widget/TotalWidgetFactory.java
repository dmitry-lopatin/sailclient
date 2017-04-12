package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

class TotalWidgetFactory extends WidgetFactory {

    @Override
    void setWidgetTitle() {
        widget.setTitle(propertiesReader.getTotalWidgetTitle());
    }

    @Override
    void setWidgetLayout() {
        widget.setLayoutX(userPreferences.getTotalWidgetLayoutX());
        widget.setLayoutY(userPreferences.getTotalWidgetLayoutY());
    }

    @Override
    void setWidgetPrefSize() {
        widget.setPrefSize(userPreferences.getTotalWidgetPrefWidth(), userPreferences.getTotalWidgetPrefHeight());
    }

    @Override
    void setWidgetMinSize() {
        widget.setMinSize(propertiesReader.getTotalWidgetMinWidth(), propertiesReader.getTotalWidgetMinHeight());
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
