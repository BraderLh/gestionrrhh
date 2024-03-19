package com.blh.gestionrrhh.util;

import com.blh.gestionrrhh.agreggates.request.RequestColaborador;
import com.blh.gestionrrhh.agreggates.request.RequestContrato;
import org.springframework.stereotype.Component;

@Component
public class ContratoValidation {
    public boolean isNullOrEmpty(String data) {
        return data != null && !data.isEmpty() && !data.isBlank();
    }

    public boolean validateInput(RequestContrato requestContrato) {
        if (requestContrato == null) return false;

        return isNullOrEmpty(requestContrato.getTipoContrato()) ||
                isNullOrEmpty(requestContrato.getBeneficios()) ||
                isNullOrEmpty(requestContrato.getDescripcion()) ||
                isNullOrEmpty(requestContrato.getDuracion()) ||
                isNullOrEmpty(requestContrato.getModalidad()) ||
                isNullOrEmpty(requestContrato.getFunciones()) ||
                isNullOrEmpty(String.valueOf(requestContrato.getRemuneracion())) ||
                isNullOrEmpty(String.valueOf(requestContrato.getColaboradorId())) ||
                isNullOrEmpty(String.valueOf(requestContrato.getEmpresaId()));
    }
}
