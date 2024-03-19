package com.blh.gestionrrhh.service;

import com.blh.gestionrrhh.agreggates.request.RequestColaborador;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradoresService {
    ResponseBase actualizarColaboradorPorId(int id, RequestColaborador requestColaborador);
    ResponseBase buscarColaboradorPorId(int id);
    ResponseBase crearColaborador(RequestColaborador requestColaborador);
    ResponseBase eliminarColaboradorPorId(int id);
    ResponseBase listarColaboradores();
}
