package com.github.jtburke.sainsburystest.adapter.sainsburys.product.model;

import com.github.jtburke.sainsburystest.domain.model.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SainsburysProductConverterTest {

    private List<SainsburysProduct> sainsburysProducts;
    private SainsburysProductConverter converter;

    @Before
    public void setUp() throws Exception {
        sainsburysProducts = Arrays.asList(
            new SainsburysProduct(
                new SainsburysProductListing(" a title ", "&pound1.99"),
                new SainsburysProductDetails(" a description ", 12345)
            ),
            new SainsburysProduct(
                new SainsburysProductListing(" b title ", "&pound2.99"),
                new SainsburysProductDetails(" b description ", 67890)
            )
        );

        converter = new SainsburysProductConverter();
    }

    @Test
    public void whenConvert_shouldConvertCorrectly() {
        assertThat(converter.convert(sainsburysProducts)).isEqualTo(Arrays.asList(
            new Product("a title", "a description", BigDecimal.valueOf(1.99), 12345),
            new Product("b title", "b description", BigDecimal.valueOf(2.99), 67890)
        ));
    }
}