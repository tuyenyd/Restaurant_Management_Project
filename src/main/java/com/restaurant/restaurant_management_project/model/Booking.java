package com.restaurant.restaurant_management_project.model;

import java.time.LocalDate;

public class Booking {
    private int id;
    private String customerName;
    private String phoneNumber;
    private LocalDate bookingDate;
    private String bookingTime;
    private int peopleCount;
    private String notes;
    private int tableId;

    public Booking() {
    }

    public Booking(int id, String customerName, String phoneNumber, LocalDate bookingDate,
                   String bookingTime, int peopleCount, String notes, int tableId) {
        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.peopleCount = peopleCount;
        this.notes = notes;
        this.tableId = tableId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bookingDate=" + bookingDate +
                ", bookingTime='" + bookingTime + '\'' +
                ", peopleCount=" + peopleCount +
                ", notes='" + notes + '\'' +
                ", tableId=" + tableId +
                '}';
    }
}
