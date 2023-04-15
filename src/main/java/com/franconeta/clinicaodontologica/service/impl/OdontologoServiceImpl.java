package com.franconeta.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franconeta.clinicaodontologica.exception.ClaveUnicaDuplicadaException;
import com.franconeta.clinicaodontologica.exception.EntidadNoEncontradaException;
import com.franconeta.clinicaodontologica.model.OdontologoModel;
import com.franconeta.clinicaodontologica.model.dto.OdontologoDTO;
import com.franconeta.clinicaodontologica.repository.IOdontologoRepository;
import com.franconeta.clinicaodontologica.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements IOdontologoService {
     @Autowired
     private IOdontologoRepository odontologoRepository;
     @Autowired
     private ObjectMapper mapper;
     private static final Logger logger = Logger.getLogger(OdontologoServiceImpl.class);
     private OdontologoDTO updateOrCreate(OdontologoDTO o) {
          OdontologoModel odontologoPersistido = odontologoRepository.save(mapper.convertValue(o, OdontologoModel.class));
          return mapper.convertValue(odontologoPersistido, OdontologoDTO.class);
     }


     //     Este metodo verifica si existe un odontologo con la misma matricula del odontologo a registrar
     private void odontologoExist(String matricula) {
          if (odontologoRepository.existsByMatricula(matricula)){
               ClaveUnicaDuplicadaException ex = new ClaveUnicaDuplicadaException("La matricula "+ matricula+ " ya esta registrada");
               logger.info(ex.getMessage());
               throw ex;
          }
     }

     @Override
     public List<OdontologoDTO> findAllOdontologos() {
          List<OdontologoModel> odontologosModel = odontologoRepository.findAll();
          List<OdontologoDTO> odontologosDTO = new ArrayList<>();

          for (OdontologoModel odontologoModel : odontologosModel) {
               odontologosDTO.add(mapper.convertValue(odontologoModel, OdontologoDTO.class));
          }
          return odontologosDTO;
     }

     @Override
     public OdontologoDTO findOdontologoById(Long id) {
          Optional<OdontologoModel> odontologo = odontologoRepository.findById(id);
          if (odontologo.isEmpty()) {
               EntidadNoEncontradaException ex = new EntidadNoEncontradaException("Odontologo con id "+id+" no fue encontrado");
               logger.info(ex.getMessage());
               throw ex;
          }
          return mapper.convertValue(odontologo.get(), OdontologoDTO.class);
     }

     @Override
     public OdontologoDTO createOdontologo(OdontologoDTO o) {
          odontologoExist(o.getMatricula()); // verifica que la matricula no este registrada
          OdontologoDTO odontologoDTO = updateOrCreate(o);
          logger.info("Odontologo con id "+ odontologoDTO.getId() + " persitido en la base de datos");
          return odontologoDTO;
     }

     @Override
     public OdontologoDTO updateOdontologo(OdontologoDTO o) {
          // TODO: 5/4/2023 falta verificar que la matricula no este ya registrada --> a implementar
          return updateOrCreate(o);
     }

     @Override
     public void deleteOdontologoById(Long id) {
          findOdontologoById(id);
          odontologoRepository.deleteById(id);
     }

     @Override
     public List<OdontologoDTO> getOdontologoByName (String nombre) {
          List<OdontologoModel> odontologosModel = odontologoRepository.findByName(nombre);
          List<OdontologoDTO> odontologosDTO = new ArrayList<>();

          for (OdontologoModel odontologoModel : odontologosModel) {
               odontologosDTO.add(mapper.convertValue(odontologoModel, OdontologoDTO.class));
          }
          return odontologosDTO;
     }

     @Override
     public List<OdontologoDTO> findByApellido(String apellido) {
          List<OdontologoModel> odontologosModel = odontologoRepository.findByApellido(apellido);
          List<OdontologoDTO> odontologosDTO = new ArrayList<>();

          for (OdontologoModel odontologoModel : odontologosModel) {
               odontologosDTO.add(mapper.convertValue(odontologoModel, OdontologoDTO.class));
          }
          return odontologosDTO;
     }
}
