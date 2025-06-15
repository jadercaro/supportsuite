package com.telco.incidents.repository;

import com.telco.incidents.model.TipoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoIncidenciaRepository extends JpaRepository<TipoIncidencia, Long> {
}