package com.example.demo.repository;

import com.example.demo.entity.CameraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** saveAll(), findAll(), findById(), existsById(), deleteById() надає Spring Data JPA. */
public interface CameraRepository extends JpaRepository<CameraEntity, Long> {
}
