package com.telco.incidents.service;

import com.telco.incidents.model.Incidencia;
import com.telco.incidents.dto.IncidenciaRequestDTO;
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

    /**
     * Crea una nueva incidencia a partir de los datos de la solicitud.
     * @param requestDTO DTO con los datos para la nueva incidencia.
     * @return La entidad Incidencia recién creada y guardada.
     */
    Incidencia crearIncidencia(IncidenciaRequestDTO requestDTO);

    /**
     * Busca una única incidencia por su ID.
     * @param id El ID de la incidencia a buscar.
     * @return La entidad Incidencia si se encuentra.
     * @throws jakarta.persistence.EntityNotFoundException si no se encuentra la incidencia.
     */
    Incidencia findById(Long id);
}