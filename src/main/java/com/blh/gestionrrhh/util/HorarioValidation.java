package com.blh.gestionrrhh.util;

import com.blh.gestionrrhh.agreggates.request.RequestHorario;
import org.springframework.stereotype.Component;

@Component
public class HorarioValidation {
    public boolean isNullOrEmpty(String data) {
        return data != null && !data.isEmpty() && !data.isBlank();
    }

    public boolean validateInput(RequestHorario requestHorario) {
        if (requestHorario == null) {
            return false;
        }
        return isNullOrEmpty(requestHorario.getDias()) ||
                isNullOrEmpty(String.valueOf(requestHorario.getHorarioInicio())) ||
                isNullOrEmpty(String.valueOf(requestHorario.getHorarioTermino()));
    }
}
