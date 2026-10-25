package com.example.demo.controller;

import com.example.demo.dto.CameraInfoDto;
import com.example.demo.dto.SnapshotDto;
import com.example.demo.entity.CameraEntity;
import com.example.demo.service.CameraService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /** СР2: отримати список камер із зовнішнього сервісу і зберегти в H2. */
    @PostMapping("/import")
    public List<CameraEntity> importCameras() {
        return cameraService.importCameras();
    }

    /** СР2: усі камери — лише з бази. */
    @GetMapping("/saved")
    public List<CameraEntity> savedCameras() {
        return cameraService.getSavedCameras();
    }

    /** СР2: одна камера або 404. */
    @ApiResponse(responseCode = "200", description = "Камеру знайдено")
    @ApiResponse(responseCode = "404", description = "Камери з таким ID немає в базі")
    @GetMapping("/saved/{id}")
    public CameraEntity savedCamera(@PathVariable Long id) {
        return cameraService.getSavedCamera(id);
    }

    /** СР2: видалення, 204 No Content. */
    @ApiResponse(responseCode = "204", description = "Камеру видалено")
    @ApiResponse(responseCode = "404", description = "Камери з таким ID немає в базі")
    @DeleteMapping("/saved/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable Long id) {
        cameraService.deleteSavedCamera(id);
        return ResponseEntity.noContent().build();
    }
}
