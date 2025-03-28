package com.restaurant.restaurant_management_project.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SideBar {
    @FXML
    private VBox sideBar;
    
    @FXML
    private ImageView logo;
    
    @FXML
    private Text name;
    
    @FXML
    public void initialize() {
        Platform.runLater(this::bindToWindow);
    }
    public void bindToWindow()
    {
        Scene scene = sideBar.getScene();
        sideBar.prefHeightProperty().bind(
                scene.heightProperty()
        );
        sideBar.prefWidthProperty().bind(
                scene.widthProperty().multiply(0.2)
        );
    }
}
