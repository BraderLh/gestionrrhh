package com.blh.gestionrrhh.util;

import com.blh.gestionrrhh.agreggates.request.RequestColaborador;
import com.blh.gestionrrhh.agreggates.request.RequestEmpresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaValidation {
    public boolean isNullOrEmpty(String data) {
        return data != null && !data.isEmpty() && !data.isBlank();
    }

    public boolean validateInput(RequestEmpresa requestEmpresa) {
        if (requestEmpresa == null) return false;

        return isNullOrEmpty(requestEmpresa.getDireccion()) ||
                isNullOrEmpty(requestEmpresa.getRuc()) ||
                isNullOrEmpty(requestEmpresa.getNombre()) ||
                isNullOrEmpty(requestEmpresa.getTelefono());
    }
}
