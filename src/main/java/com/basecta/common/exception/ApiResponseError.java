package com.basecta.common.exception;

import java.time.Instant;

public record ApiResponseError(
    Instant timestamp,
    int status,
    String error,
    String code,
    String message,
    String path
) {}
