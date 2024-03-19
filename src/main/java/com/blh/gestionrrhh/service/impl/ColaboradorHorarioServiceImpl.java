package com.blh.gestionrrhh.service.impl;

import com.blh.gestionrrhh.agreggates.constants.Constantes;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.entity.ColaboradoresEntity;
import com.blh.gestionrrhh.entity.HorarioEntity;
import com.blh.gestionrrhh.repository.ColaboradoresRepository;
import com.blh.gestionrrhh.repository.HorarioRepository;
import com.blh.gestionrrhh.service.ColaboradorHorarioService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ColaboradorHorarioServiceImpl implements ColaboradorHorarioService {
    private final ColaboradoresRepository colaboradoresRepository;
    private final HorarioRepository horarioRepository;

    public ColaboradorHorarioServiceImpl(ColaboradoresRepository colaboradoresRepository, HorarioRepository horarioRepository) {
        this.colaboradoresRepository = colaboradoresRepository;
        this.horarioRepository = horarioRepository;
    }

    @Override
    public ResponseBase agregarColaboradorHorario(Integer horarioId, Integer colaboradorId) {
        if (horarioId != null && colaboradorId != null) {
            Optional<ColaboradoresEntity> colaboradoresEntity = colaboradoresRepository.findById(horarioId);
            Optional<HorarioEntity> horarioEntity = horarioRepository.findById(horarioId);
            Set<HorarioEntity> horarioEntitySet = new HashSet<>();
            if (colaboradoresEntity.isPresent() && horarioEntity.isPresent()) {
                horarioEntitySet.add(horarioEntity.get());
                colaboradoresEntity.get().setHorarioEntitySet(horarioEntitySet);
                colaboradoresRepository.save(colaboradoresEntity.get());
            }
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(colaboradoresEntity));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
    }

    @Override
    public ResponseBase actualizarColaboradorHorario(Integer horario, Integer colaboradorId) {
        return null;
    }

    @Override
    public ResponseBase eliminarColaboradorHorario(Integer colaboradorId) {
        return null;
    }

    @Override
    public ResponseBase listarColaboradoresHorarios() {
        return null;
    }
}
