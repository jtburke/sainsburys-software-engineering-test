package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductDetails;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class JsoupSainsburysProductDetailsScraperTest {
    private static final String PRODUCT_DETAILS_URL = "URL";
    private static final int BODY_BYTES = 1024;
    private static final String DESCRIPTION = "description";

    @Mock
    private Connection connection;

    private JsoupSainsburysProductDetailsScraper detailsScraper;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Jsoup.class);
        initMocks(this);
        when(Jsoup.connect(PRODUCT_DETAILS_URL)).thenReturn(connection);
        detailsScraper = new JsoupSainsburysProductDetailsScraper();
    }

    @Test
    public void whenScrapeDetails_shouldReturnSainsburysProductDetails() throws IOException {
        Document document = mock(Document.class);

        when(connection.get()).thenReturn(document);
        setupResponse();
        setupDescription(document);

        assertThat(detailsScraper.scrapeDetails(PRODUCT_DETAILS_URL)).isEqualTo(new SainsburysProductDetails(DESCRIPTION, BODY_BYTES));
    }

    private void setupDescription(Document document) {
        Elements productDataItemHeaders = mock(Elements.class);
        Element productDataItemHeader = mock(Element.class);
        Element description = mock(Element.class);
        when(document.getElementsByClass("productDataItemHeader")).thenReturn(productDataItemHeaders);
        when(productDataItemHeaders.first()).thenReturn(productDataItemHeader);
        when(productDataItemHeader.nextElementSibling()).thenReturn(description);
        when(description.text()).thenReturn(DESCRIPTION);
    }

    private void setupResponse() {
        Connection.Response response = mock(Connection.Response.class);
        byte[] bodyAsBytes = new byte[BODY_BYTES];
        when(connection.response()).thenReturn(response);
        when(response.bodyAsBytes()).thenReturn(bodyAsBytes);
    }

    @Test(expected = GetProductsException.class)
    public void whenIOException_shouldThrowRuntimeException() throws IOException {
        when(connection.get()).thenThrow(new IOException());
        detailsScraper.scrapeDetails(PRODUCT_DETAILS_URL);
    }
}