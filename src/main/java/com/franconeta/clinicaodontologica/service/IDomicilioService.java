package com.franconeta.clinicaodontologica.service;


import com.franconeta.clinicaodontologica.model.dto.DomicilioDTO;

import java.util.List;

public interface IDomicilioService {
     List<DomicilioDTO> findAllDomicilios();
     DomicilioDTO findDomicilioById(Long id);
     DomicilioDTO createDomicilio(DomicilioDTO d);
     DomicilioDTO updateDomicilio(DomicilioDTO d);
     void deleteDomicilioById(Long id);
}
