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
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "UsuariosEntity.buscarPorEmail", query = "select u from UsuariosEntity u where u.email=:email")
public class UsuariosEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer usuarioId;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "password", length = 150, nullable = false)
    private String password;

    @Column(name = "role", length = 100, nullable = false)
    private String rol;
}
