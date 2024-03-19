package com.blh.gestionrrhh.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Audit {
    @Column(name = "status", length = 1, nullable = false)
    private int status;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "date_delete")
    private Timestamp dateDelete;

    @Column(name = "date_modify")
    private Timestamp dateModify;

    @Column(name = "user_create", length = 100)
    private String userCreate;

    @Column(name = "user_delete", length = 100)
    private String userDelete;

    @Column(name = "user_modify", length = 100)
    private String userModify;
}
