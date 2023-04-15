package com.franconeta.clinicaodontologica.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ExceptionResponseDTO {
     private LocalDateTime timeStamp;
     private String error;
     private int status;
     private String message;
}
