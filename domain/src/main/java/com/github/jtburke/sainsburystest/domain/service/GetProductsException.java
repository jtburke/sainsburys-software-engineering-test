package com.github.jtburke.sainsburystest.domain.service;

import java.io.IOException;

public class GetProductsException extends RuntimeException {
    public GetProductsException(IOException e) {
        super(e);
    }
}
