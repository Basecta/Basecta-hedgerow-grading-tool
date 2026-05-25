package com.basecta.auth.dto;

import java.util.UUID;

public record LoginResponse(
        String token,
        UUID userId,
        String username,
        String email

) {
}
