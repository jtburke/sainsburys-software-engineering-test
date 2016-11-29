package com.github.jtburke.sainsburystest.adapter.app.model;

import com.github.jtburke.sainsburystest.domain.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsResultConverter {
    public ProductsResult convert(List<Product> products) {
        return new ProductsResult(products.stream().map(p ->
            new ProductResult(
               p.getTitle(), p.getDescription(), p.getUnitPrice(), convertSize(p.getSize())
           )
        ).collect(Collectors.toList()));
    }

    private String convertSize(int size) {
        BigDecimal sizeInKb = new BigDecimal(size / 1024.0).setScale(2, BigDecimal.ROUND_HALF_UP);
        return sizeInKb + "kb";
    }
}
