package com.blh.gestionrrhh.service;

import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserService {
    ResponseBase registrar(Map<String,String> requestMap);
    ResponseBase iniciar(Map<String,String> requestMap);
    ResponseBase obtenerUsuarios();
    ResponseBase obtenerUsuarioPorEmail(String email);
    ResponseBase eliminarUsuarioPorId(int id);
}
