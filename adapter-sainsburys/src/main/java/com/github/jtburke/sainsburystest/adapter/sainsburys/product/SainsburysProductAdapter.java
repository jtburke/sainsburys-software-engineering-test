package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProductConverter;
import com.github.jtburke.sainsburystest.domain.model.product.Product;
import com.github.jtburke.sainsburystest.domain.service.ProductAdapter;

import java.util.List;

public class SainsburysProductAdapter implements ProductAdapter {
    private final SainsburysProductScraper scraper;
    private final SainsburysProductConverter converter;

    public SainsburysProductAdapter(SainsburysProductScraper scraper, SainsburysProductConverter converter) {
        this.scraper = scraper;
        this.converter = converter;
    }

    @Override
    public List<Product> getProducts(String url) {
        return converter.convert(scraper.scrapeProducts(url));
    }
}
