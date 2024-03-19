package com.blh.gestionrrhh.agreggates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestContrato {
    private String tipoContrato;
    private String descripcion;
    private double remuneracion;
    private String beneficios;
    private String duracion;
    private String modalidad;
    private String funciones;
    private int empresaId;
    private int colaboradorId;
}
