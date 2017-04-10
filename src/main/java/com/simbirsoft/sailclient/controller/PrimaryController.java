package com.simbirsoft.sailclient.controller;

import com.simbirsoft.sailclient.util.ConnectionMessage;
import com.simbirsoft.sailclient.util.UrlParam;
import com.simbirsoft.sailclient.widget.WidgetConstructor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.window.Window;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.simbirsoft.sailclient.connector.HttpConnector;
import com.simbirsoft.sailclient.util.UrlPage;

public class PrimaryController {

    @FXML
    private Pane outer;

    private Window addressWidget;
    private Window categoryWidget;
    private Window totalWidget;

    private HttpConnector httpConnector;
    private JSONParser jsonParser;

    @FXML
    public void initialize() throws Exception {

        httpConnector = new HttpConnector();
        jsonParser = new JSONParser();

        initAddressWidget();
        initCategoryWidget();
        initTotalWidget();

        fillAddressWidget(addressWidget.getContentPane().getChildren().get(0), UrlPage.GET_ALL_ADDRESSES);
        fillCategoryWidget(categoryWidget.getContentPane().getChildren().get(0), UrlPage.GET_ALL_CATEGORIES);
        fillTotalWidget(totalWidget.getContentPane().getChildren().get(0), UrlPage.GET_TOTAL_BY_ADDRESSES_AND_CATEGORIES);
    }

    private void fillAddressWidget(Node node, String url) {

        if (node.getClass() == ListView.class) {
            ListView listView = (ListView) node;
            ArrayList<CheckBox> itemCheckBoxList = new ArrayList<>();
            List<Object> jsonList;
            try {
                jsonList = getAndParseJsonToList(node, url);
            } catch (Exception e) {
                listView.setItems(FXCollections.observableArrayList(ConnectionMessage.CONNECTION_FAILURE));
                return;
            }
            jsonList.stream().forEach(address -> {
                CheckBox checkBox = new CheckBox((address.toString()));
                checkBox.setSelected(true);
                itemCheckBoxList.add(checkBox);
            });
            listView.setItems(FXCollections.observableArrayList(itemCheckBoxList));

            itemCheckBoxList.stream().forEach(checkBox -> checkBox.setOnAction(event -> {
                try {
                    updateCategories(getSelectedCheckBoxList(itemCheckBoxList));
                    updateTotal(getSelectedCheckBoxList(itemCheckBoxList),
                            getSelectedCheckBoxList(((ListView) categoryWidget.getContentPane().getChildren().get(0)).getItems()));
                    event.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    private void fillCategoryWidget(Node node, String url) {
        if (node.getClass() == ListView.class) {
            ListView listView = (ListView) node;

            ArrayList<CheckBox> itemCheckBoxList = new ArrayList<>();
            List<Object> jsonList;
            try {
                jsonList = getAndParseJsonToList(node, url);
            } catch (Exception e) {
                listView.setItems(FXCollections.observableArrayList(ConnectionMessage.CONNECTION_FAILURE));
                return;
            }
            jsonList.stream().forEach(address -> {
                CheckBox checkBox = new CheckBox((address.toString()));
                checkBox.setSelected(true);
                itemCheckBoxList.add(checkBox);
            });
            listView.setItems(FXCollections.observableArrayList(itemCheckBoxList));

            itemCheckBoxList.stream().forEach(checkBox -> checkBox.setOnAction(event -> {
                try {
                    updateTotal(
                            getSelectedCheckBoxList(((ListView) addressWidget.getContentPane().getChildren().get(0)).getItems()),
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
            ListView listView = (ListView) node;
            Double jsonDouble;
            try {
                jsonDouble = (Double) getAndParseJsonToDouble(node, url);
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

        selectedCheckBoxList.addAll(checkBoxList.stream().filter(checkBox -> checkBox.isSelected()).collect(Collectors.toList()));
        selectedTextList.addAll(selectedCheckBoxList.stream().map(checkBox -> checkBox.getText()).collect(Collectors.toList()));

        return selectedTextList;
    }

    private void updateCategories(List<String> selectedAddressesList) throws Exception {
        String url = UrlPage.GET_CATEGORIES_BY_ADDRESS_LIST + "?" + UrlParam.ADDRESSES;
        String params;

        params = String.join("&" + UrlParam.ADDRESSES, selectedAddressesList);
        String result = url + params;

        fillCategoryWidget(categoryWidget.getContentPane().getChildren().get(0), result);
    }

    private void updateTotal(List<String> selectedAddressList, List<String> selectedCategoriesList) throws Exception {
        String addressesUrl = UrlPage.GET_TOTAL_BY_ADDRESSES_AND_CATEGORIES + "?" + UrlParam.ADDRESSES;
        String categoriesUrl = "&" + UrlParam.CATEGORIES;
        String addressParams = String.join("&" + UrlParam.ADDRESSES, selectedAddressList);
        String categoryParams = String.join("&" + UrlParam.CATEGORIES, selectedCategoriesList);


        String resultUrl = addressesUrl + addressParams + categoriesUrl + categoryParams;

        fillTotalWidget(totalWidget.getContentPane().getChildren().get(0),  resultUrl);
    }

    private void initAddressWidget() {
        addressWidget = WidgetConstructor.constuct("Addresses", 150, 5, 300,
                200, 225, 100, true, true, true);
        outer.getChildren().add(addressWidget);
    }

    private void initCategoryWidget() {
        categoryWidget = WidgetConstructor.constuct("Categories", 465, 5, 200,
                200, 150, 100, true, true, true);
        outer.getChildren().add(categoryWidget);
    }

    private void initTotalWidget() {
        totalWidget = WidgetConstructor.constuct("Total", 150, 220, 200, 75,
                150, 75, true, true, true);
        outer.getChildren().add(totalWidget);
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
