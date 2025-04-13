package com.example.tuyenyd.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.tuyenyd.models.BookingStatistics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingStatisticsController {
    @FXML private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private TableView<BookingStatistics> statisticsTable;
    @FXML private TableColumn<BookingStatistics, LocalDate> dateColumn;
    @FXML private TableColumn<BookingStatistics, Integer> totalColumn;
    @FXML private TableColumn<BookingStatistics, Integer> confirmedColumn;
    @FXML private TableColumn<BookingStatistics, Integer> cancelledColumn;
    @FXML private TableColumn<BookingStatistics, Double> revenueColumn;

    private ObservableList<BookingStatistics> statisticsData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Thiết lập các cột cho TableView
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalBookings"));
        confirmedColumn.setCellValueFactory(new PropertyValueFactory<>("confirmedBookings"));
        cancelledColumn.setCellValueFactory(new PropertyValueFactory<>("cancelledBookings"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));

        // Định dạng cột doanh thu
        revenueColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double amount, boolean empty) {
                super.updateItem(amount, empty);
                if (empty || amount == null) {
                    setText(null);
                } else {
                    setText(String.format("%,.0f VND", amount));
                }
            }
        });

        // Thiết lập ngày mặc định
        fromDatePicker.setValue(LocalDate.now().minusDays(7));
        toDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void generateStatistics() {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate == null || toDate == null) {
            showAlert("Vui lòng chọn khoảng ngày thống kê");
            return;
        }

        if (fromDate.isAfter(toDate)) {
            showAlert("Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }

        // Lấy dữ liệu thống kê (trong thực tế sẽ query từ database)
        statisticsData.clear();
        statisticsData.addAll(getDummyStatisticsData(fromDate, toDate));
        statisticsTable.setItems(statisticsData);
    }

    private List<BookingStatistics> getDummyStatisticsData(LocalDate fromDate, LocalDate toDate) {
        // Dữ liệu mẫu - thực tế sẽ lấy từ database
        List<BookingStatistics> data = new ArrayList<>();

        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            int total = 5 + (int)(Math.random() * 10);
            int confirmed = (int)(total * 0.8);
            int cancelled = total - confirmed;
            double revenue = confirmed * 500000 * (0.8 + Math.random() * 0.4);

            data.add(new BookingStatistics(date, total, confirmed, cancelled, revenue));
        }

        return data;
    }

    @FXML
    private void exportToExcel() {
        // Triển khai xuất Excel
        showAlert("Chức năng xuất Excel sẽ được triển khai sau");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}