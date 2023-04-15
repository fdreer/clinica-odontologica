package com.franconeta.clinicaodontologica.model.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @Data
public class DomicilioDTO {
     private Long id;
     private String calle;
     private String numero;
     private String localidad;
     private String provincia;
}
