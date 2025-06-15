package com.telco.incidents.repository;

import com.telco.incidents.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Long> {
}