package com.telco.incidents.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "etiqueta")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etiqueta")
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;
}