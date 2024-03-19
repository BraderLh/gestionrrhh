package com.blh.gestionrrhh.service.impl;

import com.blh.gestionrrhh.agreggates.constants.Constantes;
import com.blh.gestionrrhh.agreggates.request.RequestHorario;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.entity.HorarioEntity;
import com.blh.gestionrrhh.repository.HorarioRepository;
import com.blh.gestionrrhh.service.HorarioService;
import com.blh.gestionrrhh.util.HorarioValidation;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceImpl implements HorarioService {
    private final HorarioRepository horarioRepository;
    private final HorarioValidation horarioValidation;

    public HorarioServiceImpl(HorarioRepository horarioRepository, HorarioValidation horarioValidation) {
        this.horarioRepository = horarioRepository;
        this.horarioValidation = horarioValidation;
    }

    @Override
    public ResponseBase actualizarHorarioPorId(int id, RequestHorario requestHorario) {
        boolean validationInput = horarioValidation.validateInput(requestHorario);
        if (validationInput) {
            if (horarioRepository.existsById(id)) {
                Optional<HorarioEntity> horarioEntityToUpdate = horarioRepository.findById(id);
                if (horarioEntityToUpdate.isPresent()) {
                    HorarioEntity horarioEntityUpdated = getEntityUpdate(requestHorario, horarioEntityToUpdate.get());
                    horarioRepository.save(horarioEntityUpdated);
                    return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(horarioEntityUpdated));
                } else {
                    return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_UPDATE, Optional.empty());
                }
            } else  {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
            }
        }  else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
    }

    private HorarioEntity getEntityUpdate(RequestHorario requestHorario, HorarioEntity horarioEntity) {
        horarioEntity.setDias(requestHorario.getDias());
        horarioEntity.setHoraInicio(requestHorario.getHorarioInicio());
        horarioEntity.setHoraTermino(requestHorario.getHorarioTermino());
        horarioEntity.setUserModify(Constantes.AUDIT_ADMIN);
        horarioEntity.setDateModify(new Timestamp(System.currentTimeMillis()));
        return horarioEntity;
    }

    @Override
    public ResponseBase buscarHorarioPorId(int id) {
        if (horarioRepository.existsById(id)) {
            Optional<HorarioEntity> horarioEntityToFind = horarioRepository.findById(id);
            if(horarioEntityToFind.isPresent()) {
                return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, horarioEntityToFind);
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_FIND, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    @Override
    public ResponseBase crearHorario(RequestHorario requestHorario) {
        boolean validateInput = horarioValidation.validateInput(requestHorario);
        if (validateInput) {
            HorarioEntity horarioEntity = getEntityCreate(requestHorario);
            horarioRepository.save(horarioEntity);
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(horarioEntity));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
    }

    private HorarioEntity getEntityCreate(RequestHorario requestHorario) {
        HorarioEntity horarioEntity = new HorarioEntity();
        horarioEntity.setDateCreate(new Timestamp(System.currentTimeMillis()));
        horarioEntity.setDias(requestHorario.getDias());
        horarioEntity.setHoraInicio(requestHorario.getHorarioInicio());
        horarioEntity.setHoraTermino(requestHorario.getHorarioTermino());
        horarioEntity.setStatus(Constantes.STATUS_ACTIVE);
        horarioEntity.setUserCreate(Constantes.AUDIT_ADMIN);
        return horarioEntity;
    }

    @Override
    public ResponseBase eliminarHorarioPorId(int id) {
        if (horarioRepository.existsById(id)) {
            Optional<HorarioEntity> horarioEntityToDelete = horarioRepository.findById(id);
            if (horarioEntityToDelete.isPresent()) {
                HorarioEntity horarioEntityDeleted = getEntityDelete(horarioEntityToDelete.get());
                horarioRepository.save(horarioEntityDeleted);
                return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(horarioEntityDeleted));
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_DELETE, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    private HorarioEntity getEntityDelete(HorarioEntity horarioEntity) {
        horarioEntity.setStatus(Constantes.STATUS_INACTIVE);
        horarioEntity.setUserCreate(Constantes.AUDIT_ADMIN);
        horarioEntity.setDateModify(new Timestamp(System.currentTimeMillis()));
        return horarioEntity;
    }

    @Override
    public ResponseBase listarHorarios() {
        List<HorarioEntity> horarioEntityList = horarioRepository.findAll();
        if (!horarioEntityList.isEmpty()) {
            horarioEntityList.sort(Comparator.comparingInt(HorarioEntity::getHorarioId));
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(horarioEntityList));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ZERO_ROWS, Optional.empty());
        }
    }
}
