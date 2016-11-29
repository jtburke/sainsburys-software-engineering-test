package com.github.jtburke.sainsburystest.adapter.app;

import com.github.jtburke.sainsburystest.adapter.app.model.ProductsResultConverter;
import com.github.jtburke.sainsburystest.domain.service.ProductService;

public class ConsoleApp {
    private final ProductService productService;
    private final ProductRenderer renderer;
    private final ProductsResultConverter converter;

    public ConsoleApp(ProductService productService, ProductRenderer renderer, ProductsResultConverter converter) {
        this.productService = productService;
        this.renderer = renderer;
        this.converter = converter;
    }

    public void listProducts(String url) {
        renderer.render(converter.convert(productService.getProducts(url)));
    }
}
