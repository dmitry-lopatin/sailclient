package com.simbirsoft.sailclient.controller;

import javafx.fxml.FXML;

import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.Window;

import com.simbirsoft.sailclient.widget.WidgetManager;

public class PrimaryController {

    @FXML
    private Pane root;

    private Window addressWidget;
    private Window categoryWidget;
    private Window totalWidget;

    private WidgetManager widgetManager;

    @FXML
    public void initialize() throws Exception {

        widgetManager = new WidgetManager();

        widgetManager.initWidgets(root);
        widgetManager.fillWidgets();

        addressWidget = widgetManager.getAddressWidget();
        categoryWidget = widgetManager.getCategoryWidget();
        totalWidget = widgetManager.getTotalWidget();
    }

    public void toggleAddressWidget() {
        if (addressWidget.isVisible()) {
            addressWidget.setVisible(false);
        } else {
            addressWidget.setVisible(true);
        }
    }

    public void toggleCategoryWidget() {
        if (categoryWidget.isVisible()) {
            categoryWidget.setVisible(false);
        } else {
            categoryWidget.setVisible(true);
        }
    }

    public void toggleTotalWidget() {
        if (totalWidget.isVisible()) {
            totalWidget.setVisible(false);
        } else {
            totalWidget.setVisible(true);
        }
    }

    public void resetWidgetLocation() {
        widgetManager.resetWidgetLocation();
    }

}
