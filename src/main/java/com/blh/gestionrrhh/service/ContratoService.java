package com.blh.gestionrrhh.service;

import com.blh.gestionrrhh.agreggates.request.RequestContrato;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoService {
    ResponseBase actualizarContratoPorId(int id, RequestContrato requestContrato);
    ResponseBase buscarContratoPorId(int id);
    ResponseBase crearContrato(RequestContrato requestContrato);
    ResponseBase eliminarContratoPorId(int id);
    ResponseBase listarContratos();
}
