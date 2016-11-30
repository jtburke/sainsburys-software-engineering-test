package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductConverter;
import com.github.jtburke.sainsburystest.domain.model.product.Product;
import com.github.jtburke.sainsburystest.domain.service.ProductAdapter;

import java.util.List;

public class SainsburysProductAdapter implements ProductAdapter {
    private final SainsburysProductRepository repository;
    private final SainsburysProductConverter converter;

    public SainsburysProductAdapter(SainsburysProductRepository repository, SainsburysProductConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<Product> getProducts(String url) {
        return converter.convert(repository.getProducts(url));
    }
}
