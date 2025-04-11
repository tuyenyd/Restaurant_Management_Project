package com.restaurant.restaurant_management_project.model;

/**
 *
 * @author admin
 */
public class Reservation {
    private String MaDatBan;
    private Employee employee;
    private Table table;

    public Reservation() {
    }

    public Reservation(String MaDatBan, Employee employee, Table table) {
        this.MaDatBan = MaDatBan;
        this.employee = employee;
        this.table = table;
    }
    
}
