package com.basecta.upload;

import com.basecta.config.S3Properties;
import com.basecta.upload.exception.UploadFailedException;
import com.basecta.upload.exception.UploadExceptionMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3StorageService {

    private final S3Client s3Client;
    private final S3Properties props;

    public String upload(InputStream content, String contentType, long sizeBytes) {

        String datePrefix = LocalDate.now(ZoneOffset.UTC).toString();
        String key = String.format("%s/%s", datePrefix, UUID.randomUUID());

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(props.bucket())
                .key(key)
                .contentType(contentType)
                .contentLength(sizeBytes)
                .build();

        try {
            s3Client.putObject(request, RequestBody.fromInputStream(content, sizeBytes));
        }
        catch (SdkException ex) {
            log.error("Failed to upload object to MinIO", ex);
            throw new UploadFailedException(UploadExceptionMessages.UPLOAD_FAILED, ex);
        }

        return key;
    }
}
