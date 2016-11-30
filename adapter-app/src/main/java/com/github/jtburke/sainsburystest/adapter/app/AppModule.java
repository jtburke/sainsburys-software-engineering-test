package com.github.jtburke.sainsburystest.adapter.app;

import com.github.jtburke.sainsburystest.adapter.app.model.ProductsResultConverter;
import com.github.jtburke.sainsburystest.domain.service.ProductService;

import java.util.function.Consumer;

public class AppModule {
    private final ProductService productService;
    private final Consumer<String> output;

    public AppModule(Consumer<String> output, ProductService productService) {
        this.productService = productService;
        this.output = output;
    }

    public ConsoleApp app() {
        return new ConsoleApp(productService, productRenderer(), productsResultConverter());
    }

    private ProductsResultConverter productsResultConverter() {
        return new ProductsResultConverter();
    }

    private ProductRenderer productRenderer() {
        return new JacksonProductRenderer(output);
    }
}
