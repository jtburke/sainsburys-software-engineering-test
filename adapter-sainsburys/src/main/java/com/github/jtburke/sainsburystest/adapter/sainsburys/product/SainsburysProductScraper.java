package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.product.model.SainsburysProduct;

import java.util.List;

public interface SainsburysProductScraper {
    List<SainsburysProduct> scrapeProducts(String url);
}
