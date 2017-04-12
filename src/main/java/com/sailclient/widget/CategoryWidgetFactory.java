package com.sailclient.widget;

import javafx.scene.control.ListView;

class CategoryWidgetFactory extends WidgetFactory {

    @Override
    void setWidgetTitle() {
        widget.setTitle(propertiesReader.getCategoryWidgetTitle());
    }

    @Override
    void setWidgetLayout() {
        widget.setLayoutX(userPreferences.getCategoryWidgetLayoutX());
        widget.setLayoutY(userPreferences.getCategoryWidgetLayoutY());
    }

    @Override
    void setWidgetPrefSize() {
        widget.setPrefSize(userPreferences.getCategoryWidgetPrefWidth(), userPreferences.getCategoryWidgetPrefHeight());
    }

    @Override
    void setWidgetMinSize() {
        widget.setMinSize(propertiesReader.getCategoryWidgetMinWidth(), propertiesReader.getCategoryWidgetMinHeight());
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
