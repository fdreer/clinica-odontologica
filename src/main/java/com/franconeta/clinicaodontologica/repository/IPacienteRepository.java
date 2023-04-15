package com.franconeta.clinicaodontologica.repository;

import com.franconeta.clinicaodontologica.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<PacienteModel, Long> {
     @Query("select p from PacienteModel p where p.nombre = ?1")
     List<PacienteModel> findByName(String nombre);
     Boolean existsByDni(String dni);
}
