package com.basecta.image;

import com.basecta.image.dto.ImageResponse;
import com.basecta.image.exception.ImageExceptionMessages;
import com.basecta.image.exception.ImageNotFoundException;
import com.basecta.storage.S3StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final S3StorageService s3StorageService;

    public ImageResponse getImage(UUID id) {

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(ImageExceptionMessages.IMAGE_NOT_FOUND + id));

        String presignedUrl = s3StorageService.createPresignedUrl(image.getStorageKey());

        return new ImageResponse(image.getId(), image.getOriginalFilename(), image.getContentType(), image.getSizeBytes(), image.getUploadedAt(), presignedUrl);
    }
}
