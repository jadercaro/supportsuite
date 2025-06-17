package com.telco.incidents.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "incidencias", itemRelation = "incidencia")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncidenciaResponseDTO extends RepresentationModel<IncidenciaResponseDTO> {

    private Long id;
    private SimpleClienteDTO cliente;
    private SimpleUserDTO usuarioAsignado;
    private String tipoIncidencia;
    private String resultadoIncidencia;
    private String zona;
    private LocalDateTime fecha;
    private String descripcion;
    private Set<String> etiquetas;
}