package com.franconeta.clinicaodontologica.service;


import com.franconeta.clinicaodontologica.exception.TurnoOcupadoException;
import com.franconeta.clinicaodontologica.model.dto.TurnoDTO;

import java.util.List;

public interface ITurnoService {
     List<TurnoDTO> findAllTurnos();
     TurnoDTO findTurnoById(Long id);
     TurnoDTO createTurno(TurnoDTO t) throws TurnoOcupadoException;
     TurnoDTO updateTurno(TurnoDTO t) throws TurnoOcupadoException;
     void deleteTurnoById(Long id);
}
