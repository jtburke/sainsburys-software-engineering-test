package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProduct;
import com.github.jtburke.sainsburystest.domain.service.GetProductsException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class JsoupSainsburysProductScraperTest {
    private static final String PRODUCT_LISTING_URL = "URL";

    @Mock
    private Connection connection;
    @Mock
    private JsoupSainsburysProductListingScraper listingScraper;

    private JsoupSainsburysProductScraper productScraper;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Jsoup.class);
        initMocks(this);
        when(Jsoup.connect(PRODUCT_LISTING_URL)).thenReturn(connection);
        productScraper = new JsoupSainsburysProductScraper(listingScraper);
    }

    @Test
    public void whenScrapeProducts_shouldReturnSainsburysProducts() throws IOException {
        Document document = mock(Document.class);
        Elements productListers = mock(Elements.class);
        Element productLister = mock(Element.class);
        Elements lis = mock(Elements.class);
        Element li1 = mock(Element.class);
        Element li2 = mock(Element.class);
        SainsburysProduct sainsburysProduct1 = mock(SainsburysProduct.class);
        SainsburysProduct sainsburysProduct2 = mock(SainsburysProduct.class);

        when(connection.get()).thenReturn(document);
        when(document.getElementsByClass("productLister")).thenReturn(productListers);
        when(productListers.first()).thenReturn(productLister);
        when(productLister.getElementsByTag("li")).thenReturn(lis);
        when(lis.stream()).thenReturn(Stream.of(li1, li2));
        when(listingScraper.scrapeListing(li1)).thenReturn(sainsburysProduct1);
        when(listingScraper.scrapeListing(li2)).thenReturn(sainsburysProduct2);

        assertThat(productScraper.getProducts(PRODUCT_LISTING_URL)).isEqualTo(Arrays.asList(sainsburysProduct1, sainsburysProduct2));
    }

    @Test(expected = GetProductsException.class)
    public void whenIOException_shouldThrowRuntimeException() throws IOException {
        when(connection.get()).thenThrow(new IOException());
        productScraper.getProducts(PRODUCT_LISTING_URL);
    }
}