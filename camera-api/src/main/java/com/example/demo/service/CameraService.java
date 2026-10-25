package com.example.demo.service;

import com.example.demo.client.CameraClient;
import com.example.demo.dto.CameraInfoDto;
import com.example.demo.dto.CameraItemDto;
import com.example.demo.dto.CameraListDto;
import com.example.demo.dto.SnapshotDto;
import com.example.demo.entity.CameraEntity;
import com.example.demo.repository.CameraRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CameraService {

    private final CameraClient cameraClient;
    private final CameraRepository cameraRepository;

    public CameraInfoDto getCameraInfo() {
        log.info("Getting camera info");
        CameraInfoDto response = cameraClient.getCameraInfo();
        log.info("Camera info received: {}", response);
        return response;
    }

    public SnapshotDto captureSnapshot() {
        log.info("Capturing snapshot");
        SnapshotDto response = cameraClient.captureSnapshot();
        log.info("Snapshot received");
        return response;
    }

    /** Отримує список із зовнішнього API, перетворює DTO → Entity і зберігає в H2. */
    @Transactional
    public List<CameraEntity> importCameras() {
        log.info("Importing cameras from external API");
        CameraListDto response = cameraClient.getCameras();
        if (response == null || response.cameras() == null || response.cameras().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Зовнішній сервіс повернув порожній список камер");
        }
        List<CameraEntity> entities = response.cameras().stream().map(CameraService::toEntity).toList();
        List<CameraEntity> saved = cameraRepository.saveAll(entities);
        log.info("Imported {} cameras, rows in table: {}", saved.size(), cameraRepository.count());
        return saved;
    }

    @Transactional(readOnly = true)
    public List<CameraEntity> getSavedCameras() {
        return cameraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CameraEntity getSavedCamera(Long id) {
        return cameraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Камеру з ID " + id + " не знайдено"));
    }

    @Transactional
    public void deleteSavedCamera(Long id) {
        if (!cameraRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Камеру з ID " + id + " не знайдено");
        }
        cameraRepository.deleteById(id);
        log.info("Camera {} deleted", id);
    }

    private static CameraEntity toEntity(CameraItemDto dto) {
        if (dto.id() == null || dto.name() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Некоректний запис камери від зовнішнього сервісу");
        }
        String room = dto.location() == null ? null : dto.location().room();
        Integer floor = dto.location() == null ? null : dto.location().floor();
        return new CameraEntity(dto.id(), dto.name(), dto.deviceType(), dto.status(), room, floor);
    }
}
