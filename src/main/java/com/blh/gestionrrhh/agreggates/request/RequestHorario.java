package com.blh.gestionrrhh.agreggates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestHorario {
    private int horarioInicio;
    private int horarioTermino;
    private String dias;
}
