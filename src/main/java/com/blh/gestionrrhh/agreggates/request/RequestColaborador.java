package com.blh.gestionrrhh.agreggates.request;

import com.blh.gestionrrhh.entity.HorarioEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RequestColaborador {
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
}
