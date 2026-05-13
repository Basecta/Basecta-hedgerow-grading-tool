package com.basecta.upload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "upload_links")
public class UploadLink {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, updatable = false)
    private String token;

    private String label;
    private Instant expiresAt;
    private Integer maxUploads;

    @Column(nullable = false)
    private int uploadCount;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public void registerUpload() {
        this.uploadCount++;
    }
}
