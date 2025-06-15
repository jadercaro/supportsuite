package com.telco.incidents.repository;

import com.telco.incidents.model.ResultadoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResultadoIncidenciaRepository extends JpaRepository<ResultadoIncidencia, Long> {
}