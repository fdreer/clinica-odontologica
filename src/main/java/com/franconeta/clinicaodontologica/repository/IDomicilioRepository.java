package com.franconeta.clinicaodontologica.repository;

import com.franconeta.clinicaodontologica.model.DomicilioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository<DomicilioModel, Long> {
}
