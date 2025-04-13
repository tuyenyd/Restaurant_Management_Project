package com.restaurant.restaurant_management_project..controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.restaurant.restaurant_management_project..models.RestaurantTable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookingController {
    // Các thành phần FXML hiện có
    @FXML private TextField customerName;
    @FXML private TextField phoneNumber;
    @FXML private DatePicker bookingDate;
    @FXML private ComboBox<String> bookingTime;
    @FXML private Spinner<Integer> peopleCount;
    @FXML private TextArea notes;
    @FXML private TableView<RestaurantTable> availableTablesTable;

    // Danh sách các bàn trong nhà hàng (dữ liệu mẫu)
    private final List<RestaurantTable> allTables = Arrays.asList(
            new RestaurantTable(1, "B01", "Tầng 1", 4, "Trống"),
            new RestaurantTable(2, "B02", "Tầng 1", 6, "Trống"),
            new RestaurantTable(3, "V01", "Khu vườn", 2, "Trống"),
            new RestaurantTable(4, "V02", "Khu vườn", 4, "Trống"),
            new RestaurantTable(5, "T201", "Tầng 2", 8, "Trống"),
            new RestaurantTable(6, "T202", "Tầng 2", 10, "Đã đặt"),
            new RestaurantTable(7, "VIP1", "Khu VIP", 6, "Trống"),
            new RestaurantTable(8, "VIP2", "Khu VIP", 8, "Đang sử dụng")
    );

    @FXML
    public void initialize() {
        // Thiết lập giá trị mặc định cho Spinner
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 2);
        peopleCount.setValueFactory(valueFactory);

        // Thiết lập ngày mặc định là hôm nay
        bookingDate.setValue(LocalDate.now());

        // Thêm các giờ vào ComboBox
        bookingTime.getItems().addAll(
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
                "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
                "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
                "20:00", "20:30", "21:00", "21:30", "22:00"
        );

        // Thiết lập TableView
        setupTableView();
    }

    private void setupTableView() {
        // Ẩn TableView ban đầu
        availableTablesTable.setVisible(false);
    }

    @FXML
    private void checkAvailableTables() {
        if (validateInput()) {
            // Lấy thông tin từ form
            int requiredCapacity = peopleCount.getValue();
            String selectedTime = bookingTime.getValue();
            LocalDate selectedDate = bookingDate.getValue();

            // Lọc danh sách bàn trống phù hợp
            List<RestaurantTable> availableTables = allTables.stream()
                    .filter(table -> table.getStatus().equals("Trống"))
                    .filter(table -> table.getCapacity() >= requiredCapacity)
                    .collect(Collectors.toList());

            // Hiển thị kết quả
            if (availableTables.isEmpty()) {
                showAlert("Không có bàn trống phù hợp", Alert.AlertType.INFORMATION);
            } else {
                availableTablesTable.setItems(FXCollections.observableArrayList(availableTables));
                availableTablesTable.setVisible(true);
            }
        }
    }

    private boolean validateInput() {
        if (customerName.getText().isEmpty()) {
            showAlert("Vui lòng nhập họ tên", Alert.AlertType.WARNING);
            return false;
        }

        if (phoneNumber.getText().isEmpty()) {
            showAlert("Vui lòng nhập số điện thoại", Alert.AlertType.WARNING);
            return false;
        }

        if (bookingTime.getValue() == null) {
            showAlert("Vui lòng chọn giờ đặt", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    @FXML
    private void submitBooking() {
        RestaurantTable selectedTable = availableTablesTable.getSelectionModel().getSelectedItem();

        if (selectedTable != null) {
            // Thực hiện đặt bàn
            showAlert("Đã đặt bàn " + selectedTable.getTableNumber() + " thành công!",
                    Alert.AlertType.INFORMATION);
            // Cập nhật trạng thái bàn (trong thực tế sẽ lưu vào database)
            selectedTable.setStatus("Đã đặt");
            availableTablesTable.refresh();
        } else {
            showAlert("Vui lòng chọn bàn từ danh sách", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void cancelBooking() {
        // Reset form
        customerName.clear();
        phoneNumber.clear();
        bookingTime.setValue(null);
        peopleCount.getValueFactory().setValue(2);
        notes.clear();
        availableTablesTable.setVisible(false);
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
