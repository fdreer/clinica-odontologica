package com.franconeta.clinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "odontologos")
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class OdontologoModel {
    @Id
    @SequenceGenerator(
            name = "odontologo_id_seq",
            sequenceName = "odontologo_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "odontologo_id_seq"
    )
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TurnoModel> turnos;
}
