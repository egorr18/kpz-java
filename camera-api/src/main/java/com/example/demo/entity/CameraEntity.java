package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Таблиця cameras. ID приходить із зовнішнього сервісу, тому @GeneratedValue немає:
 * повторний імпорт тієї самої камери оновлює рядок з цим ID, а не створює дубль.
 * Вкладений location зберігається двома окремими стовпцями room і floor.
 */
@Entity
@Table(name = "cameras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CameraEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "device_type")
    private String deviceType;

    private String status;

    private String room;

    private Integer floor;
}
