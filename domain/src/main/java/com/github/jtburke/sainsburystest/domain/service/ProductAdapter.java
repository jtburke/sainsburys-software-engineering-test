package com.github.jtburke.sainsburystest.domain.service;

import com.github.jtburke.sainsburystest.domain.model.product.Product;

import java.util.List;

public interface ProductAdapter {
    List<Product> getProducts(String url);
}
