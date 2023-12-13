package com.example.famerhelper;

import java.net.URI;

public class ProductInfo {
    public String hasType;
    public String description;
    public Float totalTheoriticalStock;
    public Float price;
    public String image;
    public ProductInfo() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public ProductInfo(String name,String description, Float stock, Float price,String image_id) {
        this.hasType = name;
        this.description = description;
        this.totalTheoriticalStock = stock;
        this.price = price;
        this.image = image_id;
    }

    public String getHasType() {
        return hasType;
    }

    public String getDescription() {
        return description;
    }

    public Float getTotalTheoriticalStock() {
        return totalTheoriticalStock;
    }

    public Float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
