package com.telco.incidents.service;

import com.telco.incidents.model.Incidencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IIncidenciaService {

    /**
     * Busca y filtra incidencias de forma paginada.
     * Los filtros son opcionales. Si un filtro es nulo, no se aplicará.
     *
     * @param id          ID de la incidencia para búsqueda directa (opcional).
     * @param usuarioId   ID del técnico asignado para filtrar (opcional).
     * @param tipoId      ID del tipo de incidencia para filtrar (opcional).
     * @param resultadoId ID del resultado de la incidencia para filtrar (opcional).
     * @param pageable    Objeto que contiene la información de paginación y ordenamiento.
     * @return una página (Page) de objetos Incidencia que coinciden con los criterios.
     */
    Page<Incidencia> searchIncidents(
            Long id,
            Long usuarioId,
            Long tipoId,
            Long resultadoId,
            Pageable pageable
    );
}