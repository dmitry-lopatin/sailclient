package com.simbirsoft.sailclient.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.simbirsoft.sailclient.connector.HttpConnector;
import com.simbirsoft.sailclient.util.UrlConstants;


public class PrimaryController {

    @FXML
    private Pane outer;

    @FXML
    private ListView addresses;

    @FXML
    private ListView categories;

    @FXML
    private ListView total;

    @FXML
    private CheckBox addressCheckBox;

    @FXML
    private CheckBox categoryCheckBox;

    @FXML
    private CheckBox totalCheckBox;

    private Window addressWidget;
    private Window categoryWidget;
    private Window totalWidget;

    private Stage addressStage;

    private HttpConnector httpConnector;
    private JSONParser jsonParser;



    private void fillWidget(Node node, String url) throws Exception {
        if (node instanceof ListView) {
            ListView listView = (ListView) node;
            String json = httpConnector.executeGet(url);
            JSONArray array = (JSONArray) jsonParser.parse(json);
            Object[] objects = array.toArray();
            List<Object> list = new ArrayList<>(Arrays.asList(objects));

            ArrayList<CheckBox> itemCheckBoxList = new ArrayList<>();

            list.stream().forEach(address -> {
                CheckBox checkBox = new CheckBox((address.toString()));
                checkBox.setSelected(true);
                checkBox.setOnAction(event -> {

                });
                itemCheckBoxList.add(checkBox);
            });
            listView.setItems(FXCollections.observableArrayList(itemCheckBoxList));
        }

    }

    private void fillTotalWidget(Node node, String url) throws Exception {
        if (node instanceof ListView) {
            ListView listView = (ListView) node;
            String json = httpConnector.executeGet(url);
            Double total = (Double) jsonParser.parse(json);
            listView.setItems(FXCollections.observableArrayList(total));
        }
    }

    private void UpdateCategoriesBySelectedAddresses(Node node) throws Exception{
        String url = UrlConstants.GET_CATEGORIES_BY_ADDRESS_LIST+"?addressList=";
        String params;

        ListView listView = (ListView) node;
        List<String> selectedAddressesList = listView.getItems();
        params = String.join("&addressList=", selectedAddressesList);
        String result = url+params;

        fillWidget(categoryWidget.getContentPane().getChildren().get(0), result);
    }


    private void initAddressWidget() {
        addressWidget = new Window("Addresses");
        addressWidget.setLayoutX(150);
        addressWidget.setLayoutY(5);
        addressWidget.setPrefSize(300,200);
        addressWidget.setMinSize(225, 100);
        addressWidget.setResizableWindow(true);
        addressWidget.setMovable(true);
        addressWidget.setVisible(true);
        addressWidget.getContentPane().getChildren().add(new ListView());
        outer.getChildren().add(addressWidget);
    }

    private void initCategoryWidget() {
        categoryWidget = new Window("Categories");
        categoryWidget.setLayoutX(465);
        categoryWidget.setLayoutY(5);
        categoryWidget.setPrefSize(200, 200);
        categoryWidget.setMinSize(150, 100);
        categoryWidget.setResizableWindow(true);
        categoryWidget.setMovable(true);
        categoryWidget.setVisible(true);
        categoryWidget.getContentPane().getChildren().add(new ListView());
        outer.getChildren().add(categoryWidget);
    }

    private void initTotalWidget() {
        totalWidget = new Window("Total");
        totalWidget.setLayoutX(150);
        totalWidget.setLayoutY(220);
        totalWidget.setPrefSize(200, 75);
        totalWidget.setMinSize(150, 75);
        totalWidget.setResizableWindow(true);
        totalWidget.setMovable(true);
        totalWidget.setVisible(true);
        totalWidget.getContentPane().getChildren().add(new ListView());
        outer.getChildren().add(totalWidget);
    }


    public void toggleAddressWidget() {
        if(addressWidget.isVisible()) {
            addressWidget.setVisible(false);
        } else {
            addressWidget.setVisible(true);
        }
    }

    public void toggleCategoryWidget() {
        if(categoryWidget.isVisible()) {
            categoryWidget.setVisible(false);
        } else {
            categoryWidget.setVisible(true);
        }
    }

    public void toggleTotalWidget() {
        if(totalWidget.isVisible()) {
            totalWidget.setVisible(false);
        } else {
            totalWidget.setVisible(true);
        }
    }

    public void resetWidgetLocation() {
        addressWidget.setLayoutX(150);
        addressWidget.setLayoutY(5);
        categoryWidget.setLayoutX(465);
        categoryWidget.setLayoutY(5);
        totalWidget.setLayoutX(150);
        totalWidget.setLayoutY(220);
    }





    @FXML
    public void initialize() throws Exception{
        categoryWidget = new Window("Categories");
        totalWidget = new Window("Total");

        addressStage = new Stage();
        httpConnector = new HttpConnector();
        jsonParser = new JSONParser();
        initAddressWidget();
        initCategoryWidget();
        initTotalWidget();
        fillWidget(addressWidget.getContentPane().getChildren().get(0), UrlConstants.GET_ALL_ADDRESSES);
        fillWidget(categoryWidget.getContentPane().getChildren().get(0), UrlConstants.GET_ALL_CATEGORIES);
        fillTotalWidget(totalWidget.getContentPane().getChildren().get(0), UrlConstants.GET_TOTAL_BY_ADDRESSES_AND_CATEGORIES);
    }
}
