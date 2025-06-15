package com.telco.incidents.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zona")
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;
}