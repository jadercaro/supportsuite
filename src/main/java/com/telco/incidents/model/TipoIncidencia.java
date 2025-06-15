package com.telco.incidents.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipoincidencia")
public class TipoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;
}