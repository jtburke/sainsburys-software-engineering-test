package com.github.jtburke.sainsburystest.adapter.app.model;

import com.github.jtburke.sainsburystest.domain.model.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsResultConverterTest {
    private ProductsResultConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new ProductsResultConverter();
    }

    @Test
    public void whenConvert_shouldConvertCorrectly() {
        ProductsResult converted = converter.convert(Arrays.asList(
            new Product("a title", "a description", new BigDecimal("1.23"), 1234),
            new Product("b title", "b description", new BigDecimal("4.56"), 4564)
        ));

        assertThat(converted).isEqualTo(new ProductsResult(Arrays.asList(
            new ProductResult("a title", "a description", new BigDecimal("1.23"), "1.21kb"),
            new ProductResult("b title", "b description", new BigDecimal("4.56"), "4.46kb")
        )));
    }
}