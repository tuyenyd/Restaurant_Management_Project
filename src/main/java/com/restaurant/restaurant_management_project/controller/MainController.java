package com.restaurant.restaurant_management_project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {
    @FXML
    private StackPane contentPane;

    @FXML
    public void showTableManagement() {
        loadView("/com/example/tuyenyd/views/table-management.fxml");
    }

    @FXML
    public void showBookingView() {
        loadView("/com/example/tuyenyd/views/booking-view.fxml");
    }
    @FXML
    public void showStatisticsView() {
        loadView("/com/example/tuyenyd/views/booking-statistics.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Không thể tải view: " + fxmlPath);
        }
    }
}
