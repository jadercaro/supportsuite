package com.telco.incidents.service.impl;

import com.telco.incidents.model.Incidencia;
import com.telco.incidents.repository.IIncidenciaRepository;
import com.telco.incidents.repository.specification.IncidenciaSpecification;
import com.telco.incidents.service.IIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Todas las búsquedas son de solo lectura por defecto
public class IncidenciaServiceImpl implements IIncidenciaService {

    private final IIncidenciaRepository incidenciaRepository;

    @Override
    public Page<Incidencia> searchIncidents(
            Long id,
            Long usuarioId,
            Long tipoId,
            Long resultadoId,
            Pageable pageable) {

        // 1. Empezamos con una especificación que no hace nada (devuelve todo).
        Specification<Incidencia> spec = Specification.where(null);

        // 2. Añadimos condiciones dinámicamente solo si el filtro no es nulo.
        if (id != null) {
            spec = spec.and(IncidenciaSpecification.hasId(id));
        }
        if (usuarioId != null) {
            spec = spec.and(IncidenciaSpecification.hasUsuarioId(usuarioId));
        }
        if (tipoId != null) {
            spec = spec.and(IncidenciaSpecification.hasTipoId(tipoId));
        }
        if (resultadoId != null) {
            spec = spec.and(IncidenciaSpecification.hasResultadoId(resultadoId));
        }

        // 3. Ejecutamos la consulta final con todos los filtros combinados y la paginación.
        return incidenciaRepository.findAll(spec, pageable);
    }
}