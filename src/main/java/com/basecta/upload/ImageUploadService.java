package com.basecta.upload;

import com.basecta.image.Image;
import com.basecta.image.ImageRepository;
import com.basecta.upload.dto.UploadResponse;
import com.basecta.upload.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageUploadService {

    private final UploadLinkRepository uploadLinkRepository;
    private final ImageRepository imageRepository;
    private final S3StorageService s3StorageService;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/tiff",
            "image/webp",
            "image/heic",
            "image/heif"
    );

    @Transactional
    public UploadResponse upload(MultipartFile file, String token) {

        UploadLink link = uploadLinkRepository.findByToken(token)
                .orElseThrow(() -> new UploadTokenNotFoundException(UploadExceptionMessages.TOKEN_NOT_FOUND));

        if (link.getExpiresAt() != null && link.getExpiresAt().isBefore(Instant.now())) {
            throw new UploadTokenExpiredException(UploadExceptionMessages.TOKEN_EXPIRED);
        }
        if (link.getMaxUploads() != null && link.getUploadCount() >= link.getMaxUploads()) {
            throw new UploadQuotaExceededException(UploadExceptionMessages.QUOTA_EXCEEDED);
        }
        if (file.isEmpty()) {
            throw new InvalidUploadException(UploadExceptionMessages.FILE_EMPTY);
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType.toLowerCase())) {
            throw new UnsupportedFileTypeException(UploadExceptionMessages.UNSUPPORTED_FILE_TYPE);
        }

        InputStream content;
        try {

            content = file.getInputStream();

        }
        catch (IOException ex) {
            log.error("Failed to read upload stream", ex);
            throw new UploadFailedException(UploadExceptionMessages.UPLOAD_FAILED, ex);
        }

        String key = s3StorageService.upload(content, file.getContentType(), file.getSize());

        Image image = Image.builder()
                .storageKey(key)
                .originalFilename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .sizeBytes(file.getSize())
                .build();

        Image saved = imageRepository.save(image);
        link.registerUpload();
        uploadLinkRepository.save(link);

        return new UploadResponse(saved.getId(), saved.getOriginalFilename(), saved.getSizeBytes());
    }
}
