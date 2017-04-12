package com.simbirsoft.sailclient.widget;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.web.util.UriComponentsBuilder;

import com.simbirsoft.sailclient.connector.HttpConnector;
import com.simbirsoft.sailclient.util.FabricJsonParser;
import com.simbirsoft.sailclient.util.PropertiesReader;
import com.simbirsoft.sailclient.util.UserPreferences;
import com.simbirsoft.sailclient.util.WidgetType;


public class WidgetManager {

    private Window addressWidget;
    private Window categoryWidget;
    private Window totalWidget;

    private Node addressWidgetContentNode;
    private Node categoryWidgetContentNode;
    private Node totalWidgetContentNode;

    private final HttpConnector httpConnector;
    private final JSONParser jsonParser;
    private final PropertiesReader propertiesReader;
    private final UserPreferences userPreferences;

    private WidgetManager() {
        httpConnector = HttpConnector.createInstance();
        jsonParser = FabricJsonParser.createInstance();
        propertiesReader = PropertiesReader.createInstance();
        userPreferences = UserPreferences.createInstance();
    }

    public static WidgetManager createInstance() {
        return new WidgetManager();
    }

    public void initWidgets(Pane outerPane) throws IOException{
        propertiesReader.readUrlProperties();
        initAddressWidget(outerPane);
        initCategoryWidget(outerPane);
        initTotalWidget(outerPane);

        if (addressWidget.getContentPane().getChildren().get(0) != null) {
            addressWidgetContentNode = addressWidget.getContentPane().getChildren().get(0);
        }
        if (categoryWidget.getContentPane().getChildren().get(0) != null) {
            categoryWidgetContentNode = categoryWidget.getContentPane().getChildren().get(0);
        }
        if (totalWidget.getContentPane().getChildren().get(0) != null) {
            totalWidgetContentNode = totalWidget.getContentPane().getChildren().get(0);
        }
    }

    public void fillWidgets() {
        fillAddressWidget(addressWidgetContentNode, propertiesReader.getAllAddressesUrl());
        fillCategoryWidget(categoryWidgetContentNode, propertiesReader.getAllCategoriesUrl());
        fillTotalWidget(totalWidgetContentNode, propertiesReader.getTotalByAddressesAndCategoriesUrl());
    }

    public Window getAddressWidget() {
        return addressWidget;
    }

    public Window getCategoryWidget() {
        return categoryWidget;
    }

    public Window getTotalWidget() {
        return totalWidget;
    }

    private void initAddressWidget(Pane outerPane) {
        WidgetFactory widgetFactory = new AddressWidgetFactory();
        addressWidget = widgetFactory.constructWidget();
        addressWidget.setOnMouseReleased(event -> {
            userPreferences.saveWidgetPrefs(String.valueOf(WidgetType.ADDRESS_), addressWidget.getLayoutX(),
                    addressWidget.getLayoutY(), addressWidget.getPrefWidth(), addressWidget.getPrefHeight());
        });
        outerPane.getChildren().add(addressWidget);
    }

    private void initCategoryWidget(Pane outerPane) {
        WidgetFactory widgetFactory = new CategoryWidgetFactory();
        categoryWidget = widgetFactory.constructWidget();
        categoryWidget.setOnMouseReleased(event -> {
            userPreferences.saveWidgetPrefs(String.valueOf(WidgetType.CATEGORY_), categoryWidget.getLayoutX(),
                    categoryWidget.getLayoutY(), categoryWidget.getPrefWidth(), categoryWidget.getPrefHeight());
        });
        outerPane.getChildren().add(categoryWidget);
    }

    private void initTotalWidget(Pane outerPane) {
        WidgetFactory widgetFactory = new TotalWidgetFactory();
        totalWidget = widgetFactory.constructWidget();
        totalWidget.setOnMouseReleased(event -> {
            userPreferences.saveWidgetPrefs(String.valueOf(WidgetType.TOTAL_), totalWidget.getLayoutX(),
                    totalWidget.getLayoutY(), totalWidget.getPrefWidth(), totalWidget.getPrefHeight());
        });
        outerPane.getChildren().add(totalWidget);
    }

    private void fillAddressWidget(Node node, String url) {

        if (node.getClass() == ListView.class) {

            ListView<Object> listView = (ListView<Object>) node;
            ArrayList<CheckBox> itemCheckBoxList = new ArrayList<>();
            List<Object> jsonList;
            try {
                jsonList = getAndParseJsonToList(node, url);
            } catch (Exception e) {
                listView.setItems(FXCollections.observableArrayList(propertiesReader.getConnectionFailureMessage()));
                return;
            }
            jsonList.forEach(address -> {
                CheckBox checkBox = new CheckBox((address.toString()));
                checkBox.setSelected(true);
                itemCheckBoxList.add(checkBox);
            });
            listView.setItems(FXCollections.observableArrayList(itemCheckBoxList));

            itemCheckBoxList.forEach(checkBox -> checkBox.setOnAction(event -> {
                    List<String> selectedCheckBoxList = getSelectedCheckBoxList(itemCheckBoxList);
                    updateCategories(selectedCheckBoxList);
                    updateTotal(selectedCheckBoxList, getSelectedCheckBoxList(((ListView<CheckBox>) categoryWidgetContentNode).getItems()));
                    event.consume();
            }));
        }
    }

    private void fillCategoryWidget(Node node, String url) {
        if (node.getClass() == ListView.class) {
            ListView<Object> listView = (ListView) node;

            ArrayList<CheckBox> itemCheckBoxList = new ArrayList<>();
            List<Object> jsonList;
            try {
                jsonList = getAndParseJsonToList(node, url);
            } catch (Exception e) {
                listView.setItems(FXCollections.observableArrayList(propertiesReader.getConnectionFailureMessage()));
                return;
            }
            jsonList.forEach(address -> {
                CheckBox checkBox = new CheckBox((address.toString()));
                checkBox.setSelected(true);
                itemCheckBoxList.add(checkBox);
            });
            listView.setItems(FXCollections.observableArrayList(itemCheckBoxList));

            itemCheckBoxList.forEach(checkBox -> checkBox.setOnAction(event -> {
                    updateTotal(getSelectedCheckBoxList(((ListView<CheckBox>) addressWidgetContentNode).getItems()),
                            getSelectedCheckBoxList(itemCheckBoxList));
                    event.consume();
            }));
        }
    }

    private void fillTotalWidget(Node node, String url) {
        if (node.getClass() == ListView.class) {
            ListView<Object> listView = (ListView<Object>) node;
            Double jsonDouble;
            try {
                jsonDouble = getAndParseJsonToDouble(node, url);
            } catch (Exception e) {
                listView.setItems(FXCollections.observableArrayList(propertiesReader.getConnectionFailureMessage()));
                return;
            }
            listView.setItems(FXCollections.observableArrayList(jsonDouble));
        }
    }

    private List<String> getSelectedCheckBoxList(List<CheckBox> checkBoxList) {
        List<CheckBox> selectedCheckBoxList = new ArrayList<>();
        List<String> selectedTextList = new ArrayList<>();

        selectedCheckBoxList.addAll(checkBoxList.stream().filter(CheckBox::isSelected).collect(Collectors.toList()));
        selectedTextList.addAll(selectedCheckBoxList.stream().map(Labeled::getText).collect(Collectors.toList()));

        return selectedTextList;
    }

    private void updateCategories(List<String> selectedAddressesList) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(propertiesReader.getCategoriesByAddressListUrl());
        builder.queryParam(propertiesReader.getAddressListParam(), String.join(",", selectedAddressesList));

        fillCategoryWidget(categoryWidgetContentNode, builder.build().encode().toString());
    }

    private void updateTotal(List<String> selectedAddressList, List<String> selectedCategoriesList) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(propertiesReader.getTotalByAddressesAndCategoriesUrl());
        builder.queryParam(propertiesReader.getAddressListParam(), String.join(",", selectedAddressList));
        builder.queryParam(propertiesReader.getCategoriesListParam(), String.join(",", selectedCategoriesList));

        fillTotalWidget(totalWidgetContentNode, builder.build().encode().toString());
    }

    public void resetWidgetLocation() {
        addressWidget.setLayoutX(propertiesReader.getAddressWidgetLayoutX());
        addressWidget.setLayoutY(propertiesReader.getAddressWidgetLayoutY());
        addressWidget.setPrefSize(propertiesReader.getAddressWidgetPrefWidth(), propertiesReader.getAddressWidgetPrefHeight());
        categoryWidget.setLayoutX(propertiesReader.getCategoryWidgetLayoutX());
        categoryWidget.setLayoutY(propertiesReader.getCategoryWidgetLayoutY());
        categoryWidget.setPrefSize(propertiesReader.getCategoryWidgetPrefWidth(), propertiesReader.getCategoryWidgetPrefHeight());
        totalWidget.setLayoutX(propertiesReader.getTotalWidgetLayoutX());
        totalWidget.setLayoutY(propertiesReader.getTotalWidgetLayoutY());
        totalWidget.setPrefSize(propertiesReader.getTotalWidgetPrefWidth(), propertiesReader.getTotalWidgetPrefHeight());
        userPreferences.saveWidgetPrefs(String.valueOf(WidgetType.ADDRESS_), propertiesReader.getAddressWidgetLayoutX(), propertiesReader.getAddressWidgetLayoutY(),
                propertiesReader.getAddressWidgetPrefWidth(), propertiesReader.getAddressWidgetPrefHeight());
        userPreferences.saveWidgetPrefs(String.valueOf(WidgetType.CATEGORY_), propertiesReader.getCategoryWidgetLayoutX(), propertiesReader.getCategoryWidgetLayoutY(),
                propertiesReader.getCategoryWidgetPrefWidth(), propertiesReader.getCategoryWidgetPrefHeight());
        userPreferences.saveWidgetPrefs(String.valueOf(WidgetType.TOTAL_), propertiesReader.getTotalWidgetLayoutX(), propertiesReader.getTotalWidgetLayoutY(),
                propertiesReader.getTotalWidgetPrefWidth(), propertiesReader.getTotalWidgetPrefHeight());
    }

    private List<Object> getAndParseJsonToList(Node node, String url) throws Exception {
        if (node.getClass() == ListView.class) {
            String json = httpConnector.executeGet(url);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            Object[] jsonObjects = jsonArray.toArray();
            return new ArrayList<>(Arrays.asList(jsonObjects));
        }
        return Collections.emptyList();
    }

    private double getAndParseJsonToDouble(Node node, String url) throws Exception {
        if (node.getClass() == ListView.class) {
            String json = httpConnector.executeGet(url);
            if (jsonParser.parse(json).getClass() == Double.class) {
                return (Double) jsonParser.parse(json);
            }
        }
        return 0;
    }
}
