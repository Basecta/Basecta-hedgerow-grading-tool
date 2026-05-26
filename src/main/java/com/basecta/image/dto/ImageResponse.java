package com.basecta.image.dto;

import java.time.Instant;
import java.util.UUID;

public record ImageResponse(
        UUID id,
        String originalFilename,
        String contentType,
        long sizeBytes,
        Instant uploadedAt,
        String presignedUrl
) {}
