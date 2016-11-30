package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProduct;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductDetails;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductListing;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JsoupSainsburysProductListingScraperTest {
    @Mock private
    JsoupSainsburysProductDetailsScraper detailsScraper;

    private JsoupSainsburysProductListingScraper listingScraper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        listingScraper = new JsoupSainsburysProductListingScraper(detailsScraper);
    }

    @Test
    public void whenScrapeListing_shouldReturnSainsburysProduct() {
        Element listing = mock(Element.class);

        setupLink(listing);
        setupPricePerUnit(listing);
        SainsburysProductDetails mockProductDetails = mock(SainsburysProductDetails.class);

        when(detailsScraper.scrapeDetails("url")).thenReturn(mockProductDetails);
        assertThat(listingScraper.scrapeListing(listing)).isEqualTo(new SainsburysProduct(
            new SainsburysProductListing("title", "unitPrice"), mockProductDetails
        ));
    }

    private void setupPricePerUnit(Element listing) {
        Elements pricePerUnits = mock(Elements.class);
        Element pricePerUnit = mock(Element.class);
        when(listing.getElementsByClass("pricePerUnit")).thenReturn(pricePerUnits);
        when(pricePerUnits.first()).thenReturn(pricePerUnit);
        mockTextNodeOnElement(pricePerUnit, "unitPrice");
    }

    private void setupLink(Element listing) {
        Elements links = mock(Elements.class);
        Element link = mock(Element.class);
        when(listing.getElementsByTag("a")).thenReturn(links);
        when(links.first()).thenReturn(link);
        mockTextNodeOnElement(link, "title");
        when(link.attr("href")).thenReturn("url");
    }

    private void mockTextNodeOnElement(Element element, String text) {
        TextNode textNode = mock(TextNode.class);
        when(element.textNodes()).thenReturn(Collections.singletonList(textNode));
        when(textNode.text()).thenReturn(text);
    }
}