package com.basecta.upload;

import com.basecta.upload.dto.UploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class UploadController {

    private final UploadService imageUploadService;

    @PostMapping("/{token}")
    public ResponseEntity<UploadResponse> upload(@PathVariable String token, @RequestParam("file")MultipartFile file) {

        UploadResponse response = imageUploadService.upload(file, token);

        return ResponseEntity.ok(response);

    }
}
