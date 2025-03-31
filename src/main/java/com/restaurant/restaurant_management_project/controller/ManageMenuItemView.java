package com.restaurant.restaurant_management_project.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ManageMenuItemView {
    @FXML
    private StackPane content;
    @FXML
    private Button addItemBtn;
    @FXML
    private VBox sideBar;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchBtn;
    @FXML
    private Button filterBtn;
    @FXML
    private ToggleButton nameSort;
    @FXML
    private ToggleButton priceSort;
    @FXML
    private TableView listItem;
    public void initialize() {
        Platform.runLater(this::bindToWindow);
    }
    public void bindToWindow()
    {
        Scene scene = content.getScene();
        content.prefHeightProperty().bind(
                scene.heightProperty()
        );
        content.prefWidthProperty().bind(
                scene.widthProperty().multiply(0.8)
        );
    }
}
