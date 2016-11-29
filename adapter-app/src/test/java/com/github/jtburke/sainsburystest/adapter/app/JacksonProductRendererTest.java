package com.github.jtburke.sainsburystest.adapter.app;

import com.github.jtburke.sainsburystest.adapter.app.model.ProductResult;
import com.github.jtburke.sainsburystest.adapter.app.model.ProductsResult;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.math.BigDecimal;
import java.util.Arrays;

public class JacksonProductRendererTest {
    @Test
    public void whenRender_shouldOutputPrettyJson() {
        JacksonProductRenderer renderer = new JacksonProductRenderer(this::verifyJson);

        renderer.render(new ProductsResult(Arrays.asList(
            new ProductResult("a title", "a description", new BigDecimal("1.23"), "1.21kb"),
            new ProductResult("b title", "b description", new BigDecimal("4.56"), "4.46kb")
        )));
    }

    private void verifyJson(String json) {
        try {
            JSONAssert.assertEquals(
                    "{\n" +
                            "  \"results\" : [ {\n" +
                            "    \"title\" : \"a title\",\n" +
                            "    \"size\" : \"1.21kb\",\n" +
                            "    \"unit_price\" : 1.23,\n" +
                            "    \"description\" : \"a description\"\n" +
                            "  }, {\n" +
                            "    \"title\" : \"b title\",\n" +
                            "    \"size\" : \"4.46kb\",\n" +
                            "    \"unit_price\" : 4.56,\n" +
                            "    \"description\" : \"b description\"\n" +
                            "  } ],\n" +
                            "  \"total\" : 5.79\n" +
                            "}", json, false);
        } catch (JSONException e) {
            Assert.fail(e.getMessage());
        }
    }
}