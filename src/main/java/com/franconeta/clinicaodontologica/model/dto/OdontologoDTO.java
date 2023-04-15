package com.franconeta.clinicaodontologica.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OdontologoDTO {
     private Long id;
     private String nombre;
     private String apellido;
     private String matricula;
}
