package com.blh.gestionrrhh.entity;

import com.blh.gestionrrhh.entity.common.Audit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "horario")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorarioEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer horarioId;

    @Column(name = "hora_inicio", nullable = false)
    private Integer horaInicio;

    @Column(name = "hora_termino", nullable = false)
    private Integer horaTermino;

    @Column(name = "dias", nullable = false)
    private String dias;
}
