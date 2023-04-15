package com.franconeta.clinicaodontologica.model.dto;

import com.franconeta.clinicaodontologica.model.DomicilioModel;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @Data
public class PacienteDTO {
     private Long id;
     private String nombre;
     private String apellido;
     private String dni;
     private String fechaIngreso;
     private DomicilioModel domicilio;
}
