package com.telco.incidents.repository;

import com.telco.incidents.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEtiquetaRepository extends JpaRepository<Etiqueta, Long> {
}