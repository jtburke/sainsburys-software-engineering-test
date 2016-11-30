package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProduct;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductConverter;
import com.github.jtburke.sainsburystest.domain.model.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SainsburysProductAdapterTest {
    @Mock
    private SainsburysProductConverter converter;
    @Mock
    private SainsburysProductRepository repository;

    private SainsburysProductAdapter adapter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        adapter = new SainsburysProductAdapter(repository, converter);
    }

    @Test
    public void whenGetProducts_shouldReturnProducts() {
        List<SainsburysProduct> scraped = new ArrayList<>();
        List<Product> converted = new ArrayList<>();

        when(repository.getProducts("url")).thenReturn(scraped);
        when(converter.convert(scraped)).thenReturn(converted);

        assertThat(adapter.getProducts("url")).isEqualTo(converted);
    }
}