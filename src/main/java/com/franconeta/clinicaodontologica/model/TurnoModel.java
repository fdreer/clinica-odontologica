package com.franconeta.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class TurnoModel {
     @Id
     @SequenceGenerator(
             name = "turno_id_seq",
             sequenceName = "turno_id_seq",
             initialValue = 1,
             allocationSize = 1
     )
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_id_seq")
     private Long id;

     @DateTimeFormat
     private LocalDateTime fechaHora;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "odontologo_id")
     @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
     private OdontologoModel odontologo;
     
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "paciente_id")
     @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
     private PacienteModel paciente;
}
