package com.franconeta.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franconeta.clinicaodontologica.exception.EntidadNoEncontradaException;
import com.franconeta.clinicaodontologica.exception.TurnoOcupadoException;
import com.franconeta.clinicaodontologica.model.OdontologoModel;
import com.franconeta.clinicaodontologica.model.PacienteModel;
import com.franconeta.clinicaodontologica.model.TurnoModel;
import com.franconeta.clinicaodontologica.model.dto.TurnoDTO;
import com.franconeta.clinicaodontologica.repository.ITurnoRepository;
import com.franconeta.clinicaodontologica.service.IOdontologoService;
import com.franconeta.clinicaodontologica.service.IPacienteService;
import com.franconeta.clinicaodontologica.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements ITurnoService {
     @Autowired
     private ITurnoRepository turnoRepository;
     @Autowired
     private IOdontologoService odontologoService;
     @Autowired
     private IPacienteService pacienteService;
     @Autowired
     private ObjectMapper mapper;
     private static final Logger logger = Logger.getLogger(TurnoServiceImpl.class);

//     Este metodo verifica si un turno ya esta ocupado para determinado odontologo
     private void turnoExists(LocalDateTime fechaHora, OdontologoModel odontologo) throws TurnoOcupadoException {
          Boolean turnoExistente = turnoRepository.existsByFechaHoraAndOdontologo(fechaHora, odontologo);
          if (turnoExistente) {
               TurnoOcupadoException ex = new TurnoOcupadoException("Turno de las " +fechaHora.toLocalTime()+ " hs. ocupado");
               logger.info(ex.getMessage());
               throw ex;
          }
     }
     @Override
     public List<TurnoDTO> findAllTurnos() {
          List<TurnoModel> turnosModel = turnoRepository.findAll();
          List<TurnoDTO> turnosDTO = new ArrayList<>();

          for (TurnoModel turnoModel : turnosModel) {
               turnosDTO.add(mapper.convertValue(turnoModel, TurnoDTO.class));
          }
          return turnosDTO;
     }
     @Override
     public TurnoDTO findTurnoById(Long id) {
          Optional<TurnoModel> turno = turnoRepository.findById(id);
          if (turno.isEmpty()) {
               EntidadNoEncontradaException ex = new EntidadNoEncontradaException("El turno con id " + id + " no existe");
               logger.info(ex.getMessage());
               throw ex;
          }
          return mapper.convertValue(turno.get(), TurnoDTO.class);
     }

     @Override
     public TurnoDTO createTurno(TurnoDTO t) throws TurnoOcupadoException {
          turnoExists(t.getFechaHora(), t.getOdontologo());
          OdontologoModel odontologoModel = mapper.convertValue(odontologoService.findOdontologoById(t.getOdontologo().getId()), OdontologoModel.class);
          PacienteModel pacienteModel = mapper.convertValue(pacienteService.findPacienteById(t.getPaciente().getId()), PacienteModel.class);
          t.setOdontologo(odontologoModel);
          t.setPaciente(pacienteModel);
          TurnoModel turnoPersistido = turnoRepository.save(mapper.convertValue(t, TurnoModel.class));
          logger.info("Turno con id "+ turnoPersistido.getId() + "guardado en la BD");
          return mapper.convertValue(turnoPersistido, TurnoDTO.class);
     }

     @Override
     public TurnoDTO updateTurno(TurnoDTO t) throws TurnoOcupadoException {
          return createTurno(t);
     }

     @Override
     public void deleteTurnoById(Long id) {
          findTurnoById(id);
          turnoRepository.deleteById(id);
     }
}
