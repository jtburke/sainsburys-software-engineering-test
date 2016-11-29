package com.github.jtburke.sainsburystest.adapter.app;

import com.github.jtburke.sainsburystest.adapter.app.model.ProductsResultConverter;
import com.github.jtburke.sainsburystest.domain.service.ProductService;

import java.util.function.Consumer;

public class AppModule {
    private ProductService productService;
    private Consumer<String> output;

    public AppModule(Consumer<String> output, ProductService productService) {
        this.productService = productService;
        this.output = output;
    }

    public ConsoleApp app() {
        return new ConsoleApp(productService, productRenderer(), productsResultConverter());
    }

    ProductsResultConverter productsResultConverter() {
        return new ProductsResultConverter();
    }

    ProductRenderer productRenderer() {
        return new JacksonProductRenderer(output);
    }
}
