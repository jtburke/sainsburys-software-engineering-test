package com.github.jtburke.sainsburystest.adapter.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@JsonPropertyOrder({ "title", "size", "unit_price", "description" })
public class ProductResult {
    private final String title;
    private final String description;
    private final BigDecimal unitPrice;
    private final String size;

    public ProductResult(String title, String description, BigDecimal unitPrice, String size) {
        this.title = title;
        this.description = description;
        this.unitPrice = unitPrice;
        this.size = size;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("unit_price")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("size")
    public String getSize() {
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
