package com.example.demo.client;

import com.example.demo.dto.CameraInfoDto;
import com.example.demo.dto.SnapshotDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

/** Опис запитів до зовнішнього сервісу DummyJSON; реалізацію створює OpenFeign. */
@FeignClient(name = "camera-client", url = "https://dummyjson.com/c")
public interface CameraClient {

    @GetMapping("/0716-c5cb-4391-afc3")
    CameraInfoDto getCameraInfo();

    @PutMapping("/382a-be11-44ef-8b0c")
    SnapshotDto captureSnapshot();
}
