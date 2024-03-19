package com.blh.gestionrrhh.util;

import com.blh.gestionrrhh.agreggates.request.RequestColaborador;
import org.springframework.stereotype.Component;

@Component
public class ColaboradoresValidation {
    public boolean isNullOrEmpty(String data) {
        return data != null && !data.isEmpty() && !data.isBlank();
    }

    public boolean validateInput(RequestColaborador requestColaborador) {
        if (requestColaborador == null) return false;

        return isNullOrEmpty(requestColaborador.getApellidos()) ||
                isNullOrEmpty(requestColaborador.getDni()) ||
                isNullOrEmpty(requestColaborador.getEmail()) ||
                isNullOrEmpty(requestColaborador.getNombres()) ||
                isNullOrEmpty(requestColaborador.getTelefono());
    }
}
