package com.basecta.upload.exception;

public class UploadTokenExpiredException extends RuntimeException {
    public UploadTokenExpiredException(String message) {
        super(message);
    }
}
