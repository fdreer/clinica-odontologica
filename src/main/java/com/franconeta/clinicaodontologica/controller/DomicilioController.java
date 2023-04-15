package com.franconeta.clinicaodontologica.controller;

import com.franconeta.clinicaodontologica.model.dto.DomicilioDTO;
import com.franconeta.clinicaodontologica.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {
     @Autowired
     private IDomicilioService domicilioService;

     @GetMapping
     public ResponseEntity<List<DomicilioDTO>> getAllDomicilios() {
          List<DomicilioDTO> domicilios = domicilioService.findAllDomicilios();
          return new ResponseEntity<>(domicilios, HttpStatus.OK);
     }

     @GetMapping("/{id}")
     public ResponseEntity<DomicilioDTO> getDomicilioById(@PathVariable Long id) {
          DomicilioDTO domicilio = domicilioService.findDomicilioById(id);
          return new ResponseEntity<>(domicilio, HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<DomicilioDTO> createDomicilio(@RequestBody DomicilioDTO domicilioDTO) {
          DomicilioDTO domicilio = domicilioService.createDomicilio(domicilioDTO);
          return new ResponseEntity<>(domicilio, HttpStatus.CREATED);
     }

     @PutMapping
     public ResponseEntity<DomicilioDTO> updateDomicilio(@RequestBody DomicilioDTO domicilioDTO) {
          DomicilioDTO domicilio = domicilioService.updateDomicilio(domicilioDTO);
          return new ResponseEntity<>(domicilio, HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteDomicilioById (@PathVariable Long id) {
          domicilioService.deleteDomicilioById(id);
          return new ResponseEntity<>(HttpStatus.OK);
     }
}
