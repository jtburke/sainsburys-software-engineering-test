package com.github.jtburke.sainsburystest.adapter.app;

import com.github.jtburke.sainsburystest.adapter.app.model.ProductsResult;
import com.github.jtburke.sainsburystest.adapter.app.model.ProductsResultConverter;
import com.github.jtburke.sainsburystest.domain.model.product.Product;
import com.github.jtburke.sainsburystest.domain.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConsoleAppTest {
    @Mock
    ProductService productService;

    @Mock
    ProductRenderer productRenderer;

    @Mock
    ProductsResultConverter converter;
    private ConsoleApp consoleApp;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        consoleApp = new ConsoleApp(productService, productRenderer, converter);
    }

    @Test
    public void whenListProducts_shouldRenderProductsResult() {
        List<Product> value = new ArrayList<>();
        ProductsResult productsResult = mock(ProductsResult.class);

        when(productService.getProducts("url")).thenReturn(value);
        when(converter.convert(value)).thenReturn(productsResult);

        consoleApp.listProducts("url");
        verify(productRenderer).render(productsResult);
    }
}