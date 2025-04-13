package com.example.tuyenyd.models;

import java.time.LocalDate;

public class BookingStatistics {
    private LocalDate date;
    private int totalBookings;
    private int confirmedBookings;
    private int cancelledBookings;
    private double revenue;

    public BookingStatistics(LocalDate date, int totalBookings,
                             int confirmedBookings, int cancelledBookings,
                             double revenue) {
        this.date = date;
        this.totalBookings = totalBookings;
        this.confirmedBookings = confirmedBookings;
        this.cancelledBookings = cancelledBookings;
        this.revenue = revenue;
    }

    // Getter methods
    public LocalDate getDate() { return date; }
    public int getTotalBookings() { return totalBookings; }
    public int getConfirmedBookings() { return confirmedBookings; }
    public int getCancelledBookings() { return cancelledBookings; }
    public double getRevenue() { return revenue; }

    // Property methods for JavaFX TableView
    public javafx.beans.property.SimpleObjectProperty<LocalDate> dateProperty() {
        return new javafx.beans.property.SimpleObjectProperty<>(date);
    }

    public javafx.beans.property.SimpleIntegerProperty totalBookingsProperty() {
        return new javafx.beans.property.SimpleIntegerProperty(totalBookings);
    }

    // ... Thêm các property methods tương tự cho các trường khác
}