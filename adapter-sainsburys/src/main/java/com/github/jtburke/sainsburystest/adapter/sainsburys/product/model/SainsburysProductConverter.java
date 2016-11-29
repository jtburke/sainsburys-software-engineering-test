package com.github.jtburke.sainsburystest.adapter.sainsburys.product.model;

import com.github.jtburke.sainsburystest.domain.model.product.Product;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SainsburysProductConverter {
    public List<Product> convert(List<SainsburysProduct> sainsburysProducts) {
        return sainsburysProducts.stream().map(this::convertProduct).collect(Collectors.toList());
    }

    private Product convertProduct(SainsburysProduct p) {
        return new Product(
            convertTitle(p.getListing().getTitle()),
            convertDescription(p.getDetails().getDescription()),
            convertUnitPrice(p.getListing().getUnitPrice()),
            p.getDetails().getBodyBytes()
        );
    }

    private String convertTitle(String title) {
        return StringUtils.trim(title);
    }

    private String convertDescription(String description) {
        return StringUtils.trim(description);
    }

    private BigDecimal convertUnitPrice(String unitPrice) {
        return new BigDecimal(StringUtils.trim(StringUtils.replaceAll(unitPrice, "&pound", "")));
    }
}
