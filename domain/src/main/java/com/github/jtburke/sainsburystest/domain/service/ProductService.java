package com.github.jtburke.sainsburystest.domain.service;

import com.github.jtburke.sainsburystest.domain.model.product.Product;

import java.util.List;

public class ProductService {
    private final ProductAdapter adapter;

    public ProductService(ProductAdapter adapter) {
        this.adapter = adapter;
    }


    public List<Product> getProducts(String url) {
        return adapter.getProducts(url);
    }
}
