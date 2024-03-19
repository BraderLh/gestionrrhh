package com.blh.gestionrrhh.service;

import com.blh.gestionrrhh.agreggates.request.RequestEmpresa;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaService {
    ResponseBase actualizarEmpresaPorId(int id, RequestEmpresa requestEmpresa);
    ResponseBase buscarEmpresaPorId(int id);
    ResponseBase crearEmpresa(RequestEmpresa requestEmpresa);
    ResponseBase eliminarEmpresaPorId(int id);
    ResponseBase listarEmpresas();
}
