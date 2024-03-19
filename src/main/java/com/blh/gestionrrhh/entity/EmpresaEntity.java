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
@Table(name = "empresa")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpresaEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer empresaId;

    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "telefono", length = 9, nullable = false)
    private String telefono;

    @Column(name = "direccion", nullable = false)
    private String direccion;
}
