package com.github.jtburke.sainsburystest.adapter.sainsburys;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.JsoupSainsburysProductDetailsScraper;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.JsoupSainsburysProductListingScraper;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.JsoupSainsburysProductScraper;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.SainsburysProductAdapter;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.SainsburysProductRepository;
import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductConverter;

public class SainsburysModule {
    public SainsburysProductAdapter productAdapter() {
        return new SainsburysProductAdapter(scraper(), converter());
    }

    private SainsburysProductConverter converter() {
        return new SainsburysProductConverter();
    }

    private SainsburysProductRepository scraper() {
        return new JsoupSainsburysProductScraper(listingScraper());
    }

    private JsoupSainsburysProductListingScraper listingScraper() {
        return new JsoupSainsburysProductListingScraper(detailsScraper());
    }

    private JsoupSainsburysProductDetailsScraper detailsScraper() {
        return new JsoupSainsburysProductDetailsScraper();
    }
}
