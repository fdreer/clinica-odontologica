package com.franconeta.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "domicilios")
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter @Data
public class DomicilioModel {
     @Id
     @SequenceGenerator(
             name = "domicilio_id_seq",
             sequenceName = "domicilio_id_seq",
             initialValue = 1,
             allocationSize = 1
     )
     @GeneratedValue(
             strategy = GenerationType.SEQUENCE,
             generator = "domicilio_id_seq"
     )
     private Long id;
     private String calle;
     private String numero;
     private String localidad;
     private String provincia;
     @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL)
     @JsonIgnore
     private Set<PacienteModel> pacientes;
}
