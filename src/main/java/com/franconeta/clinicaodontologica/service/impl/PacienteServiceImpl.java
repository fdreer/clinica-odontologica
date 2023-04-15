package com.franconeta.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franconeta.clinicaodontologica.exception.ClaveUnicaDuplicadaException;
import com.franconeta.clinicaodontologica.exception.EntidadNoEncontradaException;
import com.franconeta.clinicaodontologica.model.DomicilioModel;
import com.franconeta.clinicaodontologica.model.PacienteModel;
import com.franconeta.clinicaodontologica.model.dto.DomicilioDTO;
import com.franconeta.clinicaodontologica.model.dto.PacienteDTO;
import com.franconeta.clinicaodontologica.repository.IPacienteRepository;
import com.franconeta.clinicaodontologica.service.IDomicilioService;
import com.franconeta.clinicaodontologica.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService {
     @Autowired
     private IPacienteRepository pacienteRepository;
     @Autowired
     private IDomicilioService domicilioService;
     @Autowired
     private ObjectMapper mapper;
     private static final Logger logger = Logger.getLogger(PacienteServiceImpl.class);

//     Este metodo verifica si existe un paciente con el mismo dni del paciente a registrar
     private void pacienteExist(String dni) {
          if (pacienteRepository.existsByDni(dni)) {
               ClaveUnicaDuplicadaException ex = new ClaveUnicaDuplicadaException("El paciente con el dni "+dni+" ya existe");
               logger.info(ex.getMessage());
               throw ex;
          }
     }

     private PacienteDTO updateOrCreate(PacienteDTO p) {
          DomicilioDTO domicilioDTO = domicilioService.findDomicilioById(p.getDomicilio().getId());
          DomicilioModel domicilioModel = mapper.convertValue(domicilioDTO, DomicilioModel.class);
          p.setDomicilio(domicilioModel);
          PacienteModel pacientePersistido = pacienteRepository.save(mapper.convertValue(p, PacienteModel.class));
          return mapper.convertValue(pacientePersistido, PacienteDTO.class);
     }

     @Override
     public List<PacienteDTO> findAllPacientes() {
          List<PacienteModel> pacientesModel = pacienteRepository.findAll();
          List<PacienteDTO> pacientesDTO = new ArrayList<>();

          for (PacienteModel pacienteModel : pacientesModel) {
               pacientesDTO.add(mapper.convertValue(pacienteModel, PacienteDTO.class));
          }
          return pacientesDTO;
     }

     @Override
     public PacienteDTO findPacienteById(Long id) {
          Optional<PacienteModel> paciente = pacienteRepository.findById(id); //.orElseThrow()
          if (paciente.isPresent()) {
               return mapper.convertValue(paciente.get(), PacienteDTO.class);
          }

          EntidadNoEncontradaException ex = new EntidadNoEncontradaException("El paciente con id " + id + " no existe");
          logger.info(ex.getMessage());
          throw ex;
     }

     @Override
     public PacienteDTO createPaciente(PacienteDTO p) {
          pacienteExist(p.getDni());
          PacienteDTO pacienteDTO = updateOrCreate(p);
          logger.info("Se crea paciente con id "+ pacienteDTO.getId());
          return pacienteDTO;
     }

     @Override
     public PacienteDTO updatePaciente(PacienteDTO p) {
//          findPacienteById(p.getId()); // para verificar que exista el paciente con dicho id --> todo --> esta linea me da error
          PacienteDTO pacienteDTO = updateOrCreate(p);
          logger.info("Se modifica paciente con id "+ pacienteDTO.getId());
          return pacienteDTO;
     }

     @Override
     public List<PacienteDTO> getPacienteByName (String nombre) {
          List<PacienteModel> pacientesModel = pacienteRepository.findByName(nombre);
          List<PacienteDTO> pacientesDTO = new ArrayList<>();

          for (PacienteModel pacienteModel : pacientesModel) {
               pacientesDTO.add(mapper.convertValue(pacienteModel, PacienteDTO.class));
          }
          return pacientesDTO;
     }

     @Override
     public void deletePacienteById(Long id) {
          findPacienteById(id);
          pacienteRepository.deleteById(id);
          logger.info("Se elimino paciente con id "+ id);
     }
}
