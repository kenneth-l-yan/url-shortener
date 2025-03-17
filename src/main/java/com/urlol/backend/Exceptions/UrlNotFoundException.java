package com.urlol.backend.Exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String shortId) {
        super("No URL found with short ID of " + shortId);
    }
}
