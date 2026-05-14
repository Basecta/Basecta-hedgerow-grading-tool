package com.basecta.common.exception;

import com.basecta.upload.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UploadTokenNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleUploadTokenNotFound(UploadTokenNotFoundException ex, HttpServletRequest request) {
        log.debug("Upload link not found: {}", ex.getMessage());

        ApiResponseError body = new ApiResponseError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                "TOKEN_NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(UploadTokenExpiredException.class)
    public ResponseEntity<ApiResponseError> handleUploadTokenExpired(UploadTokenExpiredException ex, HttpServletRequest request) {
        log.debug("Upload link expired: {}", ex.getMessage());

        ApiResponseError body = new ApiResponseError(
                Instant.now(),
                HttpStatus.GONE.value(),
                HttpStatus.GONE.getReasonPhrase(),
                "TOKEN_EXPIRED",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.GONE).body(body);
    }

    @ExceptionHandler(UploadQuotaExceededException.class)
    public ResponseEntity<ApiResponseError> handleUploadQuotaExceeded(UploadQuotaExceededException ex, HttpServletRequest request) {
        log.debug("Upload quota exceeded for upload link: {}", ex.getMessage());

        ApiResponseError body = new ApiResponseError(
                Instant.now(),
                HttpStatus.TOO_MANY_REQUESTS.value(),
                HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase(),
                "QUOTA_EXCEEDED",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(body);
    }

    @ExceptionHandler(InvalidUploadException.class)
    public ResponseEntity<ApiResponseError> handleInvalidUpload(InvalidUploadException ex, HttpServletRequest request) {
        log.debug("Invalid upload: {}", ex.getMessage());

        ApiResponseError body = new ApiResponseError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "FILE_EMPTY",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(UnsupportedFileTypeException.class)
    public ResponseEntity<ApiResponseError> handleUnsupportedFileType(UnsupportedFileTypeException ex, HttpServletRequest request) {
        log.debug("Unsupported file type: {}", ex.getMessage());

        ApiResponseError body = new ApiResponseError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "UNSUPPORTED_FILE_TYPE",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(UploadFailedException.class)
    public ResponseEntity<ApiResponseError> handleUploadFailed(UploadFailedException ex, HttpServletRequest request) {
        log.debug("Upload failed: {}", ex.getMessage());

        ApiResponseError body = new ApiResponseError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "UPLOAD_FAILED",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError> handleUnexpected(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception: {}", ex.getMessage());

        ApiResponseError body = new ApiResponseError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "UNHANDLED_EXCEPTION",
                "An unexpected error occurred",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
