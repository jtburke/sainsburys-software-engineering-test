package com.github.jtburke.sainsburystest.adapter.sainsburys.product.model;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class SainsburysProduct {
    private final SainsburysProductListing listing;
    private final SainsburysProductDetails details;

    public SainsburysProduct(SainsburysProductListing listing, SainsburysProductDetails details) {
        this.listing = listing;
        this.details = details;
    }

    public SainsburysProductListing getListing() {
        return listing;
    }

    public SainsburysProductDetails getDetails() {
        return details;
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
