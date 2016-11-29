package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductDetails;
import com.github.jtburke.sainsburystest.domain.service.GetProductsException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupSainsburysProductDetailsScraper {
    public SainsburysProductDetails scrapeDetails(String url) {
        Connection connection = Jsoup.connect(url);

        try {
            Document document = connection.get();
            Elements productDataItemHeader = document.getElementsByClass("productDataItemHeader");
            Element description = productDataItemHeader.first().nextElementSibling();

            return new SainsburysProductDetails(
                description.text(),
                connection.response().bodyAsBytes().length
            );
        } catch (IOException e) {
            throw new GetProductsException(e);
        }
    }
}
