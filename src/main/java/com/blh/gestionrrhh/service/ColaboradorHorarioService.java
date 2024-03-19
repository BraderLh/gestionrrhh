package com.blh.gestionrrhh.service;

import com.blh.gestionrrhh.agreggates.response.ResponseBase;

public interface ColaboradorHorarioService {
    ResponseBase agregarColaboradorHorario(Integer horarioId, Integer colaboradorId);
    ResponseBase actualizarColaboradorHorario(Integer horario, Integer colaboradorId);
    ResponseBase eliminarColaboradorHorario(Integer colaboradorId);
    ResponseBase listarColaboradoresHorarios();
}
