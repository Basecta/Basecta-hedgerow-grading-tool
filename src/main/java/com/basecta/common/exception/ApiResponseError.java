package com.basecta.common.exception;

import java.time.Instant;
import java.util.List;

public record ApiResponseError(
    Instant timestamp,
    int status,
    String error,
    String code,
    String message,
    String path,
    List<FieldError> fieldErrors
) {}
