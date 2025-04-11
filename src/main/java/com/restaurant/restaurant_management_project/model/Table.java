package com.restaurant.restaurant_management_project.model;

/**
 *
 * @author admin
 */
public class Table {
    private String MaBan;
    private int SoGhe;
    private String GhiChu;

    public Table() {
    }

    public Table(String MaBan, int SoGhe, String GhiChu) {
        this.MaBan = MaBan;
        this.SoGhe = SoGhe;
        this.GhiChu = GhiChu;
    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public int getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(int SoGhe) {
        this.SoGhe = SoGhe;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
