package com.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product implements Cloneable {
    private Long id;
    private BigDecimal price;
    private String productName;
    private int num;

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return null;
    }
}
