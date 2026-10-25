package com.example.demo.dto;

import java.util.List;

/** Відповідь зовнішнього сервісу: об'єкт із масивом cameras. */
public record CameraListDto(List<CameraItemDto> cameras) {
}
