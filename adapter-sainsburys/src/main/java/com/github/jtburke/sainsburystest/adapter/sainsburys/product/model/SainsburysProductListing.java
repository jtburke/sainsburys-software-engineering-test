package com.github.jtburke.sainsburystest.adapter.sainsburys.product.model;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class SainsburysProductListing {
    private final String title;
    private final String unitPrice;

    public SainsburysProductListing(String title, String unitPrice) {
        this.title = title;
        this.unitPrice = unitPrice;
    }

    public String getTitle() {
        return title;
    }

    public String getUnitPrice() {
        return unitPrice;
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
