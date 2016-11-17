package com.socnet.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Cannot access the resource");
    }
}
