package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProduct;
import com.github.jtburke.sainsburystest.domain.service.GetProductsException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JsoupSainsburysProductScraper implements SainsburysProductRepository {
    private final JsoupSainsburysProductListingScraper listingScraper;

    public JsoupSainsburysProductScraper(JsoupSainsburysProductListingScraper listingScraper) {
        this.listingScraper = listingScraper;
    }

    @Override
    public List<SainsburysProduct> getProducts(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            Element productLister = document.getElementsByClass("productLister").first();

            return productLister.getElementsByTag("li")
                    .stream()
                    .map(listingScraper::scrapeListing)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new GetProductsException(e);
        }
    }
}
