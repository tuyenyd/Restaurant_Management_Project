package com.example.tuyenyd.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.tuyenyd.models.RestaurantTable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Arrays;

public class TableManagementController {
    @FXML private TableView<RestaurantTable> tablesTableView;
    @FXML private TableColumn<RestaurantTable, Integer> idColumn;
    @FXML private TableColumn<RestaurantTable, String> tableNumberColumn;
    @FXML private TableColumn<RestaurantTable, String> areaColumn;
    @FXML private TableColumn<RestaurantTable, Integer> capacityColumn;
    @FXML private TableColumn<RestaurantTable, String> statusColumn;

    private ObservableList<RestaurantTable> tables = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Thiết lập các cột cho TableView
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load dữ liệu mẫu
        loadSampleData();

        // Thêm cột hành động
        addActionColumn();
    }

    private void loadSampleData() {
        tables.addAll(Arrays.asList(
                new RestaurantTable(1, "B01", "Tầng 1", 4, "Trống"),
                new RestaurantTable(2, "B02", "Tầng 1", 6, "Đã đặt"),
                new RestaurantTable(3, "V01", "Khu vườn", 2, "Trống"),
                new RestaurantTable(4, "V02", "Khu vườn", 4, "Đang sử dụng"),
                new RestaurantTable(5, "T201", "Tầng 2", 8, "Trống"),
                new RestaurantTable(6, "T202", "Tầng 2", 10, "Đã đặt"),
                new RestaurantTable(7, "VIP1", "Khu VIP", 6, "Trống"),
                new RestaurantTable(8, "VIP2", "Khu VIP", 8, "Trống")
        ));

        tablesTableView.setItems(tables);
    }

    private void addActionColumn() {
        TableColumn<RestaurantTable, Void> actionColumn = new TableColumn<>("Hành động");

        actionColumn.setCellFactory(param -> new TableCell<RestaurantTable, Void>() {
            private final Button editButton = new Button("Sửa");
            private final Button deleteButton = new Button("Xóa");

            {
                editButton.getStyleClass().add("primary-button");
                deleteButton.getStyleClass().add("danger-button");

                editButton.setOnAction(event -> {
                    RestaurantTable table = getTableView().getItems().get(getIndex());
                    editTable(table);
                });

                deleteButton.setOnAction(event -> {
                    RestaurantTable table = getTableView().getItems().get(getIndex());
                    deleteTable(table);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(5, editButton, deleteButton));
                }
            }
        });

        tablesTableView.getColumns().add(actionColumn);
    }

    @FXML
    private void showAddTableDialog() {
        // Tạo dialog thêm bàn mới
        Dialog<RestaurantTable> dialog = new Dialog<>();
        dialog.setTitle("Thêm bàn mới");
        dialog.setHeaderText("Nhập thông tin bàn");

        // Thiết lập các nút
        ButtonType addButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Tạo form nhập liệu
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField tableNumberField = new TextField();
        TextField areaField = new TextField();
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 20, 4);
        ComboBox<String> statusCombo = new ComboBox<>();
        statusCombo.getItems().addAll("Trống", "Đã đặt", "Đang sử dụng");
        statusCombo.setValue("Trống");

        grid.add(new Label("Số bàn:"), 0, 0);
        grid.add(tableNumberField, 1, 0);
        grid.add(new Label("Khu vực:"), 0, 1);
        grid.add(areaField, 1, 1);
        grid.add(new Label("Số chỗ:"), 0, 2);
        grid.add(capacitySpinner, 1, 2);
        grid.add(new Label("Trạng thái:"), 0, 3);
        grid.add(statusCombo, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Chuyển đổi kết quả khi nhấn nút Thêm
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new RestaurantTable(
                        0, // ID tạm, sẽ được tạo khi lưu vào database
                        tableNumberField.getText(),
                        areaField.getText(),
                        capacitySpinner.getValue(),
                        statusCombo.getValue()
                );
            }
            return null;
        });

        // Xử lý kết quả
        dialog.showAndWait().ifPresent(newTable -> {
            // Thêm vào danh sách (thực tế sẽ lưu vào database)
            newTable.setId(tables.size() + 1);
            tables.add(newTable);
            showAlert("Đã thêm bàn mới thành công!", Alert.AlertType.INFORMATION);
        });
    }

    private void editTable(RestaurantTable table) {
        // Tạo dialog chỉnh sửa thông tin bàn
        Dialog<RestaurantTable> dialog = new Dialog<>();
        dialog.setTitle("Chỉnh sửa thông tin bàn");
        dialog.setHeaderText("Chỉnh sửa thông tin bàn " + table.getTableNumber());

        // Thiết lập các nút
        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Tạo form nhập liệu
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField tableNumberField = new TextField(table.getTableNumber());
        TextField areaField = new TextField(table.getArea());
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 20, table.getCapacity());
        ComboBox<String> statusCombo = new ComboBox<>();
        statusCombo.getItems().addAll("Trống", "Đã đặt", "Đang sử dụng");
        statusCombo.setValue(table.getStatus());

        grid.add(new Label("Số bàn:"), 0, 0);
        grid.add(tableNumberField, 1, 0);
        grid.add(new Label("Khu vực:"), 0, 1);
        grid.add(areaField, 1, 1);
        grid.add(new Label("Số chỗ:"), 0, 2);
        grid.add(capacitySpinner, 1, 2);
        grid.add(new Label("Trạng thái:"), 0, 3);
        grid.add(statusCombo, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Chuyển đổi kết quả khi nhấn nút Lưu
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                table.setTableNumber(tableNumberField.getText());
                table.setArea(areaField.getText());
                table.setCapacity(capacitySpinner.getValue());
                table.setStatus(statusCombo.getValue());
                return table;
            }
            return null;
        });

        // Xử lý kết quả
        dialog.showAndWait().ifPresent(updatedTable -> {
            // Cập nhật TableView (thực tế sẽ lưu vào database)
            tablesTableView.refresh();
            showAlert("Đã cập nhật thông tin bàn thành công!", Alert.AlertType.INFORMATION);
        });
    }

    private void deleteTable(RestaurantTable table) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa bàn " + table.getTableNumber() + "?");
        alert.setContentText("Hành động này không thể hoàn tác.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                tables.remove(table);
                showAlert("Đã xóa bàn thành công!", Alert.AlertType.INFORMATION);
            }
        });
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}