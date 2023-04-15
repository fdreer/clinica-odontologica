package com.franconeta.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "pacientes")
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Data
public class PacienteModel {
     @Id
     @SequenceGenerator(
             name = "paciente_id_seq",
             sequenceName = "paciente_id_seq",
             initialValue = 1,
             allocationSize = 1
     )
     @GeneratedValue(
             strategy = GenerationType.SEQUENCE,
             generator = "paciente_id_seq"
     )
     private Long id;
     private String nombre;
     private String apellido;
     private String dni;
     private String fechaIngreso;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "domicilio_id")
     @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
     private DomicilioModel domicilio;

     @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
     @JsonIgnore
     private Set<TurnoModel> turnos;
}
