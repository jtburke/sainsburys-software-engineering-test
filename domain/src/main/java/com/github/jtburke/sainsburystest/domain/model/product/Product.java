package com.github.jtburke.sainsburystest.domain.model.product;

import java.math.BigDecimal;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class Product {
    private final String title;
    private final String description;
    private final BigDecimal unitPrice;
    private final int size;

    public Product(String title, String description, BigDecimal unitPrice, int size) {
        this.title = title;
        this.description = description;
        this.unitPrice = unitPrice;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
