package com.max.banco1.excepction;

public class CuentaNotFoundException extends RuntimeException{

    public CuentaNotFoundException(String message) {
        super(message);
    }
}
