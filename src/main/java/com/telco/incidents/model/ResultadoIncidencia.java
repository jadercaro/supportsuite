package com.telco.incidents.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "resultadoincidencia")
public class ResultadoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Long id;

    @Column(unique = true, nullable = false)
    private String descripcion;
}