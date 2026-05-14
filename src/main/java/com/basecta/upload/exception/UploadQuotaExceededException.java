package com.basecta.upload.exception;

public class UploadQuotaExceededException extends RuntimeException {
    public UploadQuotaExceededException(String message) {
        super(message);
    }
}
