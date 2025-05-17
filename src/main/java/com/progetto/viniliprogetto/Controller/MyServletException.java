package com.progetto.viniliprogetto.Controller;

import javax.servlet.ServletException;

public class MyServletException extends ServletException {
    private final int status;

    public MyServletException(String message) {
        this(message, 400);  // Bad request status
    }

    public MyServletException(String message, int status) {
        super(message);
        this.status = status;
    }

}
