package com.franconeta.clinicaodontologica.controller;

import com.franconeta.clinicaodontologica.model.dto.OdontologoDTO;
import com.franconeta.clinicaodontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
     @Autowired
     private IOdontologoService odontologoService;

     @GetMapping
     public ResponseEntity<List<OdontologoDTO>> getAllOdontologos () {
          List<OdontologoDTO> odontologos = odontologoService.findAllOdontologos();
          return new ResponseEntity<>(odontologos, HttpStatus.OK);
     }

     @GetMapping("/{id}")
     public ResponseEntity<OdontologoDTO> getOdontologoById(@PathVariable Long id) {
          OdontologoDTO odontologo = odontologoService.findOdontologoById(id);
          return new ResponseEntity<>(odontologo, HttpStatus.OK);
     }

     @GetMapping("/buscar-nombre/{nombre}")
     public List<OdontologoDTO> getOdontologoByName (@PathVariable String nombre) {
          return odontologoService.getOdontologoByName(nombre);
     }

     @GetMapping("/apellido/{apellido}")
     public List<OdontologoDTO> findByApellido (@PathVariable String apellido) {
          return odontologoService.findByApellido(apellido);
     }

     @PostMapping
     public ResponseEntity<OdontologoDTO> createOdontologo(@RequestBody OdontologoDTO o) {
          OdontologoDTO odontologoModel = odontologoService.createOdontologo(o);
          return new ResponseEntity<>(odontologoModel, HttpStatus.CREATED);
     }

     @PutMapping
     public ResponseEntity<OdontologoDTO> updateOdontologo(@RequestBody OdontologoDTO o) {
          OdontologoDTO odontologoModel = odontologoService.updateOdontologo(o);
          return new ResponseEntity<>(odontologoModel, HttpStatus.CREATED);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteOdontologoById (@PathVariable Long id) {
          odontologoService.deleteOdontologoById(id);
          return new ResponseEntity<>(HttpStatus.OK);
     }
}
