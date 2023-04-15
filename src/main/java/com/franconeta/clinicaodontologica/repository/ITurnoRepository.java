package com.franconeta.clinicaodontologica.repository;

import com.franconeta.clinicaodontologica.model.OdontologoModel;
import com.franconeta.clinicaodontologica.model.TurnoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ITurnoRepository extends JpaRepository<TurnoModel, Long> {
     Boolean existsByFechaHoraAndOdontologo(LocalDateTime dateTimeOfTurno, OdontologoModel odontologo);
}