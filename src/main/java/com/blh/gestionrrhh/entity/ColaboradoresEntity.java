package com.blh.gestionrrhh.entity;

import com.blh.gestionrrhh.entity.common.Audit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "colaboradores")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColaboradoresEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer colaboradorId;

    @Column(name = "dni", unique = true, length = 8, nullable = false)
    private String dni;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefono", unique = true, length = 9, nullable = false)
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "colaborador_horario",
            joinColumns = @JoinColumn(name = "id_colaborador"),
            inverseJoinColumns = @JoinColumn(name = "id_horario")
    )
    private Set<HorarioEntity> horarioEntitySet = new HashSet<>();
}
