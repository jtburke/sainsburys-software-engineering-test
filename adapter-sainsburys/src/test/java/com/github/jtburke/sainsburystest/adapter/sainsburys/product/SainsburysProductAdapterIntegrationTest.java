package com.github.jtburke.sainsburystest.adapter.sainsburys.product;

import com.github.jtburke.sainsburystest.adapter.sainsburys.SainsburysModule;
import com.github.jtburke.sainsburystest.domain.model.product.Product;
import com.github.jtburke.sainsburystest.domain.service.ProductAdapter;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.assertj.core.api.Assertions.assertThat;

public class SainsburysProductAdapterIntegrationTest {
    @ClassRule
    public static WireMockRule wireMockStaticRule = new WireMockRule();

    @Rule
    public WireMockRule wireMockRule = wireMockStaticRule;

    private ProductAdapter productAdapter;

    @Before
    public void setUp() throws Exception {
        stub("5_products");
        stub("sainsburys-apricot-ripe---ready-320g");
        stub("sainsburys-avocado--ripe---ready-x2");
        stub("sainsburys-avocado-xl-pinkerton-loose-300g");
        stub("sainsburys-avocados--ripe---ready-x4");
        stub("sainsburys-conference-pears--ripe---ready-x4-%28minimum%29");
        stub("sainsburys-golden-kiwi--taste-the-difference-x4-685641-p-44");
        stub("sainsburys-kiwi-fruit--ripe---ready-x4");

        productAdapter = new SainsburysModule().productAdapter();
    }

    private void stub(String file) {
        stubFor(get(urlMatching(".*/" + file + ".html"))
            .willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBodyFile("sainsburys/" + file + ".html")
                    )
        );
    }

    @Test
    public void whenGetProducts_shouldReturnProducts() {
        List<Product> products = productAdapter.getProducts("http://localhost:8080/2015_Developer_Scrape/5_products.html");

        assertThat(products).isEqualTo(Arrays.asList(
            new Product("Sainsbury's Apricot Ripe & Ready x5", "Apricots", new BigDecimal("3.50"), 35643),
            new Product("Sainsbury's Avocado Ripe & Ready XL Loose 300g", "Avocados", new BigDecimal("1.50"), 36054),
            new Product("Sainsbury's Avocado, Ripe & Ready x2", "Avocados", new BigDecimal("1.80"), 40653),
            new Product("Sainsbury's Avocados, Ripe & Ready x4", "Avocados", new BigDecimal("3.20"), 36067),
            new Product("Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)", "Conference", new BigDecimal("1.50"), 35923),
            new Product("Sainsbury's Golden Kiwi x4", "Gold Kiwi", new BigDecimal("1.80"), 35943),
            new Product("Sainsbury's Kiwi Fruit, Ripe & Ready x4", "Kiwi", new BigDecimal("1.80"), 36368)
        ));
    }
}
