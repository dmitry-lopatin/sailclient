package com.simbirsoft.sailclient.widget;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.simbirsoft.sailclient.connector.HttpConnector;
import com.simbirsoft.sailclient.util.ConnectionMessage;
import com.simbirsoft.sailclient.util.UrlPage;
import com.simbirsoft.sailclient.util.UrlParam;

public class WidgetManager {

    private Window addressWidget;
    private Window categoryWidget;
    private Window totalWidget;

    private final HttpConnector httpConnector;
    private final JSONParser jsonParser;

    public WidgetManager() {
        httpConnector = new HttpConnector();
        jsonParser = new JSONParser();
    }

    public void initWidgets(Pane outerPane) {
        initAddressWidget(outerPane);
        initCategoryWidget(outerPane);
        initTotalWidget(outerPane);
    }

    public void fillWidgets() {
        fillAddressWidget(addressWidget.getContentPane().getChildren().get(0), UrlPage.GET_ALL_ADDRESSES);
        fillCategoryWidget(categoryWidget.getContentPane().getChildren().get(0), UrlPage.GET_ALL_CATEGORIES);
        fillTotalWidget(totalWidget.getContentPane().getChildren().get(0), UrlPage.GET_TOTAL_BY_ADDRESSES_AND_CATEGORIES);
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
        addressWidget = WidgetConstructor.construct("Addresses", 150, 5, 300,
                200, 225, 100, true, true, true);
        outerPane.getChildren().add(addressWidget);
    }

    private void initCategoryWidget(Pane outerPane) {
        categoryWidget = WidgetConstructor.construct("Categories", 465, 5, 200,
                200, 150, 100, true, true, true);
        outerPane.getChildren().add(categoryWidget);
    }

    private void initTotalWidget(Pane outerPane) {
        totalWidget = WidgetConstructor.construct("Total", 150, 220, 200, 75,
                150, 75, true, true, true);
        outerPane.getChildren().add(totalWidget);
    }

    private void fillAddressWidget(Node node, String url) {

        if (node.getClass() == ListView.class) {
            ListView<Object> listView = (ListView) node;
            ArrayList<CheckBox> itemCheckBoxList = new ArrayList<>();
            List<Object> jsonList;
            try {
                jsonList = getAndParseJsonToList(node, url);
            } catch (Exception e) {
                listView.setItems(FXCollections.observableArrayList(ConnectionMessage.CONNECTION_FAILURE));
                return;
            }
            jsonList.forEach(address -> {
                CheckBox checkBox = new CheckBox((address.toString()));
                checkBox.setSelected(true);
                itemCheckBoxList.add(checkBox);
            });
            listView.setItems(FXCollections.observableArrayList(itemCheckBoxList));

            itemCheckBoxList.forEach(checkBox -> checkBox.setOnAction(event -> {
                try {
                    List<String> selectedCheckBoxList = getSelectedCheckBoxList(itemCheckBoxList);
                    updateCategories(selectedCheckBoxList);
                    updateTotal(selectedCheckBoxList, getSelectedCheckBoxList(((ListView<CheckBox>) categoryWidget.getContentPane().getChildren().get(0)).getItems()));
                    event.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                listView.setItems(FXCollections.observableArrayList(ConnectionMessage.CONNECTION_FAILURE));
                return;
            }
            jsonList.forEach(address -> {
                CheckBox checkBox = new CheckBox((address.toString()));
                checkBox.setSelected(true);
                itemCheckBoxList.add(checkBox);
            });
            listView.setItems(FXCollections.observableArrayList(itemCheckBoxList));

            itemCheckBoxList.forEach(checkBox -> checkBox.setOnAction(event -> {
                try {
                    updateTotal(getSelectedCheckBoxList(((ListView<CheckBox>) addressWidget.getContentPane().getChildren().get(0)).getItems()),
                            getSelectedCheckBoxList(itemCheckBoxList));
                    event.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                listView.setItems(FXCollections.observableArrayList(ConnectionMessage.CONNECTION_FAILURE));
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
        String url = UrlPage.GET_CATEGORIES_BY_ADDRESS_LIST + "?" + UrlParam.ADDRESSES;
        String params;

        params = String.join("&" + UrlParam.ADDRESSES, selectedAddressesList);
        String result = url + params;

        fillCategoryWidget(categoryWidget.getContentPane().getChildren().get(0), result);
    }

    private void updateTotal(List<String> selectedAddressList, List<String> selectedCategoriesList) {
        String addressesUrl = UrlPage.GET_TOTAL_BY_ADDRESSES_AND_CATEGORIES + "?" + UrlParam.ADDRESSES;
        String categoriesUrl = "&" + UrlParam.CATEGORIES;
        String addressParams = String.join("&" + UrlParam.ADDRESSES, selectedAddressList);
        String categoryParams = String.join("&" + UrlParam.CATEGORIES, selectedCategoriesList);

        String resultUrl = addressesUrl + addressParams + categoriesUrl + categoryParams;

        fillTotalWidget(totalWidget.getContentPane().getChildren().get(0), resultUrl);
    }

    public void resetWidgetLocation() {
        addressWidget.setLayoutX(150);
        addressWidget.setLayoutY(5);
        addressWidget.setPrefSize(300, 200);
        categoryWidget.setLayoutX(465);
        categoryWidget.setLayoutY(5);
        categoryWidget.setPrefSize(200, 200);
        totalWidget.setLayoutX(150);
        totalWidget.setLayoutY(220);
        totalWidget.setPrefSize(200, 75);
    }

    private List<Object> getAndParseJsonToList(Node node, String url) throws Exception {
        if (node.getClass() == ListView.class) {
            String json = httpConnector.executeGet(url);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            Object[] jsonObjects = jsonArray.toArray();
            return new ArrayList<>(Arrays.asList(jsonObjects));
        }
        return Collections.EMPTY_LIST;
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
