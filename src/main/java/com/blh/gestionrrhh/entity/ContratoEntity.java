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
@Table(name = "contrato")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContratoEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer contratoId;

    @Column(name = "tipo_contrato", nullable = false)
    private String tipoContrato;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "remuneracion", nullable = false)
    private Double remuneracion;

    @Column(name = "beneficios", nullable = false)
    private String beneficios;

    @Column(name = "duracion", nullable = false)
    private String duracion;

    @Column(name = "modalidad", nullable = false)
    private String modalidad;

    @Column(name = "funciones", nullable = false)
    private String funciones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private EmpresaEntity empresaEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
    private ColaboradoresEntity colaboradoresEntity;
}
