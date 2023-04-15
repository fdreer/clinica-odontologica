package com.franconeta.clinicaodontologica.controller;

import com.franconeta.clinicaodontologica.exception.TurnoOcupadoException;
import com.franconeta.clinicaodontologica.model.dto.TurnoDTO;
import com.franconeta.clinicaodontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
     @Autowired
     private ITurnoService turnoService;

     @GetMapping
     public ResponseEntity<List<TurnoDTO>> getTurnos () {
          List<TurnoDTO> turnos = turnoService.findAllTurnos();
          return new ResponseEntity<>(turnos, HttpStatus.OK);
     }

     @GetMapping("/{id}")
     public ResponseEntity<TurnoDTO> getTurnoById(@PathVariable Long id) {
          TurnoDTO turno = turnoService.findTurnoById(id);
          return new ResponseEntity<>(turno, HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<TurnoDTO> createTurno(@RequestBody TurnoDTO t) throws TurnoOcupadoException {
          TurnoDTO turno = turnoService.createTurno(t);
          return new ResponseEntity<>(turno, HttpStatus.CREATED);
     }

     @PutMapping
     public ResponseEntity<TurnoDTO> updateTurno(@RequestBody TurnoDTO t) throws TurnoOcupadoException {
          TurnoDTO turno = turnoService.updateTurno(t);
          return new ResponseEntity<>(turno, HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteTurnoById (@PathVariable Long id) {
          turnoService.deleteTurnoById(id);
          return new ResponseEntity<>(HttpStatus.OK);
     }
}
