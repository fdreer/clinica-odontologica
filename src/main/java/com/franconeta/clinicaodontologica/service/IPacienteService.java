package com.franconeta.clinicaodontologica.service;


import com.franconeta.clinicaodontologica.model.dto.PacienteDTO;

import java.util.List;

public interface IPacienteService{
     List<PacienteDTO> findAllPacientes();
     PacienteDTO findPacienteById(Long id);
     List<PacienteDTO> getPacienteByName (String nombre);
     PacienteDTO createPaciente(PacienteDTO p);
     PacienteDTO updatePaciente(PacienteDTO p);
     void deletePacienteById(Long id);
}
