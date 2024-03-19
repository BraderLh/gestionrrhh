package com.blh.gestionrrhh.service;

import com.blh.gestionrrhh.agreggates.request.RequestHorario;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioService {
    ResponseBase actualizarHorarioPorId(int id, RequestHorario requestHorario);
    ResponseBase buscarHorarioPorId(int id);
    ResponseBase crearHorario(RequestHorario requestHorario);
    ResponseBase eliminarHorarioPorId(int id);
    ResponseBase listarHorarios();
}
