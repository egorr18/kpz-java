package com.example.demo.dto;

import java.util.List;

/** JSON-відповідь з інформацією про камеру; device_type збігається з назвою поля в JSON. */
public record CameraInfoDto(
        Integer id,
        String device_type,
        String status,
        List<String> features,
        Location location
) {
    public record Location(String room, Integer floor) {
    }
}
