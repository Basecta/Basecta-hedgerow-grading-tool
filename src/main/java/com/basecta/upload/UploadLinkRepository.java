package com.basecta.upload;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UploadLinkRepository extends JpaRepository<UploadLink, UUID> {
    Optional<UploadLink> findByToken(String token);
}
