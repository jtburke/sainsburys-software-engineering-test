package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProduct;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductListing;
import org.jsoup.nodes.Element;

public class JsoupSainsburysProductListingScraper {
    private final JsoupSainsburysProductDetailsScraper detailsScraper;

    public JsoupSainsburysProductListingScraper(JsoupSainsburysProductDetailsScraper detailsScraper) {
        this.detailsScraper = detailsScraper;
    }

    public SainsburysProduct scrapeListing(Element listing) {
        Element link = listing.getElementsByTag("a").first();
        Element pricePerUnit = listing.getElementsByClass("pricePerUnit").first();

        return new SainsburysProduct(
            new SainsburysProductListing(
                link.textNodes().get(0).text(),
                pricePerUnit.textNodes().get(0).text()
            ),
            detailsScraper.scrapeDetails(link.attr("href"))
        );
    }
}
