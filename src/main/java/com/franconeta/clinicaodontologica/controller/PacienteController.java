package com.franconeta.clinicaodontologica.controller;

import com.franconeta.clinicaodontologica.model.dto.PacienteDTO;
import com.franconeta.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
     @Autowired
     private IPacienteService pacienteService;

     @GetMapping
     public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
          List<PacienteDTO> pacientes = pacienteService.findAllPacientes();
          return new ResponseEntity<>(pacientes, HttpStatus.OK);
     }

     @GetMapping("/{id}")
     public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
          PacienteDTO paciente = pacienteService.findPacienteById(id);
          return new ResponseEntity<>(paciente, HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<PacienteDTO> createPaciente (@RequestBody PacienteDTO pacienteDTO) {
          PacienteDTO paciente = pacienteService.createPaciente(pacienteDTO);
          return new ResponseEntity<>(paciente, HttpStatus.CREATED);
     }

     @PutMapping
     public ResponseEntity<PacienteDTO> updatePaciente(@RequestBody PacienteDTO pacienteDTO) {
          PacienteDTO paciente = pacienteService.updatePaciente(pacienteDTO);
          return new ResponseEntity<>(paciente,HttpStatus.OK);
     }

     @GetMapping("/buscar-nombre/{nombre}")
     public ResponseEntity<?> getPacienteByName(@PathVariable String nombre) {
          List<PacienteDTO> pacientes = pacienteService.getPacienteByName(nombre);

          if (pacientes.size() > 0) {
               return new ResponseEntity<>(pacientes, HttpStatus.OK);
          } else {
               return new ResponseEntity<>(pacientes, HttpStatus.NO_CONTENT);
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deletePacienteById(@PathVariable Long id) {
          pacienteService.deletePacienteById(id);
          return new ResponseEntity<>(HttpStatus.OK);
     }
}
