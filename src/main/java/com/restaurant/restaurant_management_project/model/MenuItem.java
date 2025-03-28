package com.restaurant.restaurant_management_project.model;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class MenuItem {
    String code;
    String name;
    Image image;
    BigDecimal price;
    float vat;
    String unit;
    String category;
    MenuItem sideDish;
    boolean status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MenuItem getSideDish() {
        return sideDish;
    }

    public void setSideDish(MenuItem sideDish) {
        this.sideDish = sideDish;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
