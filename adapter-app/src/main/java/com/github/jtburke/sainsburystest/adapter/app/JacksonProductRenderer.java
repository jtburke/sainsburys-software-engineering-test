package com.github.jtburke.sainsburystest.adapter.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jtburke.sainsburystest.adapter.app.model.ProductsResult;
import com.github.jtburke.sainsburystest.domain.service.GetProductsException;

import java.util.function.Consumer;

public class JacksonProductRenderer implements ProductRenderer {
    private final ObjectMapper mapper;
    private Consumer<String> output;

    public JacksonProductRenderer(Consumer<String> output) {
        this.output = output;
        mapper = new ObjectMapper();
    }

    @Override
    public void render(ProductsResult products) {
        try {
            output.accept(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));
        } catch (JsonProcessingException e) {
            throw new GetProductsException(e);
        }
    }
}
