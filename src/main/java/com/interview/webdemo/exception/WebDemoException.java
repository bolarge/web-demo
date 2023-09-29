package com.interview.webdemo.exception;

import java.util.List;

public class WebDemoException extends RuntimeException {
    public WebDemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebDemoException(List<String> errors) {
        super();
    }
}
