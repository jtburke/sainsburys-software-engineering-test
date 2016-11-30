package com.github.jtburke.sainsburystest.domain;

import com.github.jtburke.sainsburystest.domain.service.ProductAdapter;
import com.github.jtburke.sainsburystest.domain.service.ProductService;

public class DomainModule {
    private final ProductAdapter productAdapter;

    public DomainModule(ProductAdapter productAdapter) {
        this.productAdapter = productAdapter;
    }

    public ProductService productService() {
        return new ProductService(productAdapter);
    }
}
