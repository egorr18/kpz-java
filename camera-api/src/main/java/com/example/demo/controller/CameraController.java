package com.example.demo.controller;

import com.example.demo.dto.CameraInfoDto;
import com.example.demo.dto.SnapshotDto;
import com.example.demo.service.CameraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/camera")
@RequiredArgsConstructor
@Tag(name = "Camera")
public class CameraController {

    private final CameraService cameraService;

    @GetMapping("/info")
    public CameraInfoDto info() {
        return cameraService.getCameraInfo();
    }

    @PostMapping(
            value = "/snapshot",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> captureSnapshot() {
        SnapshotDto snapshot = cameraService.captureSnapshot();
        byte[] image = Base64.getDecoder().decode(snapshot.image());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}
