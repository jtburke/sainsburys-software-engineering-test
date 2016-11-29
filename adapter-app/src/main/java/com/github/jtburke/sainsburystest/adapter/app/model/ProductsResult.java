package com.github.jtburke.sainsburystest.adapter.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class ProductsResult {
    private final List<ProductResult> results;

    public ProductsResult(List<ProductResult> results) {
        this.results = results;
    }

    @JsonProperty("results")
    public List<ProductResult> getResults() {
        return results;
    }

    @JsonProperty("total")
    public BigDecimal total() {
        return results.parallelStream().map(ProductResult::getUnitPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
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
