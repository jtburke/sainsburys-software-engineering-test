package com.github.jtburke.sainsburystest.adapter.sainsburys.product.model;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class SainsburysProductDetails {
    private final String description;
    private final int bodyBytes;

    public SainsburysProductDetails(String description, int bodyBytes) {
        this.description = description;
        this.bodyBytes = bodyBytes;
    }

    public String getDescription() {
        return description;
    }

    public int getBodyBytes() {
        return bodyBytes;
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
