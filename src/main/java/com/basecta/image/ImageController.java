package com.basecta.image;

import com.basecta.image.dto.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> getImage(@PathVariable UUID id) {

        ImageResponse response = imageService.getImage(id);

        return ResponseEntity.ok(response);
    }
}
