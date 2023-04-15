package com.franconeta.clinicaodontologica.model.dto;

import com.franconeta.clinicaodontologica.model.OdontologoModel;
import com.franconeta.clinicaodontologica.model.PacienteModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TurnoDTO {
     private Long id;
     private LocalDateTime fechaHora;
     private OdontologoModel odontologo;
     private PacienteModel paciente;
}
