package com.franconeta.clinicaodontologica.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franconeta.clinicaodontologica.model.DomicilioModel;
import com.franconeta.clinicaodontologica.model.dto.DomicilioDTO;
import com.franconeta.clinicaodontologica.repository.IDomicilioRepository;
import com.franconeta.clinicaodontologica.service.IDomicilioService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImpl implements IDomicilioService {
     @Autowired
     private IDomicilioRepository domicilioRepository;
     @Autowired
     private ObjectMapper mapper;
     private static final Logger logger = Logger.getLogger(DomicilioServiceImpl.class);

     @Override
     public DomicilioDTO findDomicilioById(Long id) {
          Optional<DomicilioModel> domicilioModel = domicilioRepository.findById(id);
          if (domicilioModel.isEmpty()) {
               EntityNotFoundException ex = new EntityNotFoundException("Domicilio con id "+ id + " no fue econtrado");
               logger.info(ex.getMessage());
               throw ex;
          }
          return mapper.convertValue(domicilioModel.get(), DomicilioDTO.class);
     }

     @Override
     public DomicilioDTO createDomicilio(DomicilioDTO domicilioDTO) {
          DomicilioModel domicilioGuardado = domicilioRepository.save(mapper.convertValue(domicilioDTO, DomicilioModel.class));
          logger.info("Se crea domicilio con id "+domicilioGuardado.getId());
          return mapper.convertValue(domicilioGuardado, DomicilioDTO.class);
     }

     @Override
     public DomicilioDTO updateDomicilio(DomicilioDTO domicilioDTO) {
          return createDomicilio(domicilioDTO);
     }

     @Override
     public List<DomicilioDTO> findAllDomicilios() {
          List<DomicilioModel> domiciliosModel = domicilioRepository.findAll();
          List<DomicilioDTO> domiciliosDTO = new ArrayList<>();

          for (DomicilioModel domicilioModel : domiciliosModel) {
               domiciliosDTO.add(mapper.convertValue(domicilioModel, DomicilioDTO.class));
          }
          return domiciliosDTO;
     }

     @Override
     public void deleteDomicilioById(Long id) {
          findDomicilioById(id);
          domicilioRepository.deleteById(id);
     }
}
