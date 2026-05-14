package com.basecta.upload.dto;

import java.util.UUID;

public record UploadResponse(
        UUID id,
        String originalFilename,
        long sizeBytes
) {}
