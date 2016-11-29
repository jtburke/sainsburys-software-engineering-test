package com.github.jtburke.sainsburystest.acceptencetest;

import com.github.jtburke.sainsburystest.adapter.app.AppModule;
import com.github.jtburke.sainsburystest.adapter.app.ConsoleApp;
import com.github.jtburke.sainsburystest.adapter.sainsburys.SainsburysModule;
import com.github.jtburke.sainsburystest.domain.DomainModule;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

public class AcceptanceTest {
    private ConsoleApp app;
    private String output;

    @Before
    public void setUp() throws Exception {
        app = new AppModule(
            in -> output = in,
            new DomainModule(new SainsburysModule().productAdapter()).productService()
        ).app();
    }

    @Test
    public void whenGetProducts_shouldReturnProducts() throws IOException, JSONException {
        app.listProducts("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");

        JSONAssert.assertEquals(Resources.toString(Resources.getResource("products.json"), Charsets.UTF_8), output, true);
    }
}
