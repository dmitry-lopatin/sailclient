package com.simbirsoft.sailclient.widget;

import javafx.scene.control.ListView;

class AddressWidgetFactory extends WidgetFactory {

    @Override
    void setWidgetTitle() {
        widget.setTitle(propertiesReader.getAddressWidgetTitle());
    }

    @Override
    void setWidgetLayout() {
        widget.setLayoutX(userPreferences.getAddressWidgetLayoutX());
        widget.setLayoutY(userPreferences.getAddressWidgetLayoutY());
    }

    @Override
    void setWidgetPrefSize() {
        widget.setPrefSize(userPreferences.getAddressWidgetPrefWidth(), userPreferences.getAddressWidgetPrefHeight());
    }

    @Override
    void setWidgetMinSize() {
        widget.setMinSize(propertiesReader.getAddressWidgetMinWidth(), propertiesReader.getAddressWidgetMinHeight());
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
