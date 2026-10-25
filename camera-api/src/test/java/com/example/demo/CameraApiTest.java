package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.client.CameraClient;
import com.example.demo.dto.CameraInfoDto;
import com.example.demo.dto.CameraItemDto;
import com.example.demo.dto.CameraListDto;
import com.example.demo.dto.SnapshotDto;
import java.util.Base64;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/** Інтеграційний тест без зовнішньої мережі: Feign-клієнт підмінено, база — H2 у пам'яті. */
@SpringBootTest
@AutoConfigureMockMvc
class CameraApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CameraClient cameraClient;

    private static CameraListDto twoCameras() {
        return new CameraListDto(List.of(
                new CameraItemDto(1L, "Main Entrance", "IP_CAMERA", "ONLINE", new CameraInfoDto.Location("Hall", 1)),
                new CameraItemDto(2L, "Parking", "IP_CAMERA", "OFFLINE", new CameraInfoDto.Location("Parking", 0))));
    }

    @Test
    void snapshotIsDecodedToJpeg() throws Exception {
        byte[] jpeg = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0, 1, 2, 3};
        when(cameraClient.captureSnapshot()).thenReturn(new SnapshotDto(Base64.getEncoder().encodeToString(jpeg)));
        mvc.perform(post("/api/v1/camera/snapshot"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "image/jpeg"))
                .andExpect(content().bytes(jpeg));
    }

    @Test
    void importReadDeleteAndReimportWithoutDuplicates() throws Exception {
        when(cameraClient.getCameras()).thenReturn(twoCameras());

        mvc.perform(post("/api/v1/camera/import")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
        mvc.perform(get("/api/v1/camera/saved/1")).andExpect(status().isOk()).andExpect(jsonPath("$.room").value("Hall"));
        mvc.perform(delete("/api/v1/camera/saved/1")).andExpect(status().isNoContent());
        mvc.perform(get("/api/v1/camera/saved/1")).andExpect(status().isNotFound());
        mvc.perform(delete("/api/v1/camera/saved/1")).andExpect(status().isNotFound());

        mvc.perform(post("/api/v1/camera/import")).andExpect(status().isOk());
        mvc.perform(post("/api/v1/camera/import")).andExpect(status().isOk());
        mvc.perform(get("/api/v1/camera/saved")).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void emptyExternalResponseGives502() throws Exception {
        when(cameraClient.getCameras()).thenReturn(new CameraListDto(List.of()));
        mvc.perform(post("/api/v1/camera/import")).andExpect(status().isBadGateway());
    }
}
