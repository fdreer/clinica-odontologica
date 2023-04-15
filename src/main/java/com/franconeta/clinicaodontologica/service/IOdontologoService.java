package com.franconeta.clinicaodontologica.service;

import com.franconeta.clinicaodontologica.model.dto.OdontologoDTO;

import java.util.List;

public interface IOdontologoService {
     List<OdontologoDTO> findAllOdontologos();
     OdontologoDTO findOdontologoById(Long id);
     OdontologoDTO createOdontologo(OdontologoDTO o);
     OdontologoDTO updateOdontologo(OdontologoDTO o);
     void deleteOdontologoById(Long id);
     List<OdontologoDTO> getOdontologoByName (String nombre);
     List<OdontologoDTO> findByApellido(String apellido);
}
