package com.github.jtburke.sainsburystest.domain.service;

import com.github.jtburke.sainsburystest.domain.model.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceTest {
    @Mock
    private ProductAdapter adapter;

    private ProductService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new ProductService(adapter);
    }

    @Test
    public void whenGetProducts_shouldReturnProductsFromAdapter() {
        List<Product> products = new ArrayList<>();
        when(adapter.getProducts("url")).thenReturn(products);
        assertThat(service.getProducts("url")).isEqualTo(products);
    }
}