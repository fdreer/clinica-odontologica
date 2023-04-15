package com.franconeta.clinicaodontologica.repository;

import com.franconeta.clinicaodontologica.model.OdontologoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<OdontologoModel, Long> {
     @Query("select o from OdontologoModel o where o.nombre like :nombre%")
     List<OdontologoModel> findByName(String nombre);
     List<OdontologoModel> findByApellido(String apellido);
     Boolean existsByMatricula(String matricula);
}
