package com.blh.gestionrrhh.service.impl;

import com.blh.gestionrrhh.agreggates.constants.Constantes;
import com.blh.gestionrrhh.agreggates.request.RequestColaborador;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.entity.ColaboradoresEntity;
import com.blh.gestionrrhh.repository.ColaboradoresRepository;
import com.blh.gestionrrhh.repository.HorarioRepository;
import com.blh.gestionrrhh.service.ColaboradoresService;
import com.blh.gestionrrhh.util.ColaboradoresValidation;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradoresServiceImpl implements ColaboradoresService {
    private final ColaboradoresRepository colaboradoresRepository;
    private final ColaboradoresValidation colaboradoresValidation;

    public ColaboradoresServiceImpl(ColaboradoresRepository colaboradoresRepository, ColaboradoresValidation colaboradoresValidation) {
        this.colaboradoresRepository = colaboradoresRepository;
        this.colaboradoresValidation = colaboradoresValidation;
    }

    @Override
    public ResponseBase actualizarColaboradorPorId(int id, RequestColaborador requestColaborador) {
        boolean validarInput = colaboradoresValidation.validateInput(requestColaborador);
        if (validarInput) {
            if (colaboradoresRepository.existsById(id)) {
                Optional<ColaboradoresEntity> colaboradoresEntity = colaboradoresRepository.findById(id);
                if (colaboradoresEntity.isPresent()) {
                    ColaboradoresEntity colaboradorUpdate = getEntityUpdate(requestColaborador, colaboradoresEntity.get());
                    colaboradoresRepository.save(colaboradorUpdate);
                    return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(colaboradorUpdate));
                } else {
                    return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_UPDATE, Optional.empty());
                }
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
    }

    private ColaboradoresEntity getEntityUpdate(RequestColaborador requestColaborador, ColaboradoresEntity colaboradoresEntity) {
        colaboradoresEntity.setApellidos(requestColaborador.getApellidos());
        colaboradoresEntity.setDni(requestColaborador.getDni());
        colaboradoresEntity.setNombres(requestColaborador.getNombres());
        colaboradoresEntity.setEmail(requestColaborador.getApellidos());
        colaboradoresEntity.setTelefono(requestColaborador.getTelefono());
        colaboradoresEntity.setDateModify(new Timestamp(System.currentTimeMillis()));
        colaboradoresEntity.setUserModify(Constantes.AUDIT_ADMIN);
        return colaboradoresEntity;
    }

    @Override
    public ResponseBase buscarColaboradorPorId(int id) {
        if (colaboradoresRepository.existsById(id)) {
            Optional<ColaboradoresEntity> colaboradoresEntity = colaboradoresRepository.findById(id);
            if (colaboradoresEntity.isPresent()) return new ResponseBase(Constantes.CODE_SUCCESS,
                    Constantes.MESS_SUCCESS, colaboradoresEntity);
            else return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_FIND, Optional.empty());
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    @Override
    public ResponseBase crearColaborador(RequestColaborador requestColaborador) {
        boolean validarInput = colaboradoresValidation.validateInput(requestColaborador);
        if (validarInput) {
            ColaboradoresEntity colaboradoresEntity = getColaboradorEntity(requestColaborador);
            colaboradoresRepository.save(colaboradoresEntity);
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(colaboradoresEntity));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
    }

    private ColaboradoresEntity getColaboradorEntity(RequestColaborador requestColaborador) {
        ColaboradoresEntity colaboradoresEntity = new ColaboradoresEntity();
        colaboradoresEntity.setApellidos(requestColaborador.getApellidos());
        colaboradoresEntity.setDni(requestColaborador.getDni());
        colaboradoresEntity.setNombres(requestColaborador.getNombres());
        colaboradoresEntity.setEmail(requestColaborador.getApellidos());
        colaboradoresEntity.setTelefono(requestColaborador.getTelefono());
        colaboradoresEntity.setStatus(Constantes.STATUS_ACTIVE);
        colaboradoresEntity.setDateCreate(new Timestamp(System.currentTimeMillis()));
        colaboradoresEntity.setUserCreate(Constantes.AUDIT_ADMIN);
        return colaboradoresEntity;
    }

    @Override
    public ResponseBase eliminarColaboradorPorId(int id) {
        if (colaboradoresRepository.existsById(id)) {
            Optional<ColaboradoresEntity> colaboradoresEntity = colaboradoresRepository.findById(id);
            if (colaboradoresEntity.isPresent()) {
                ColaboradoresEntity colaboradorEntityDeleted = getEntityDelete(colaboradoresEntity.get());
                colaboradoresRepository.save(colaboradorEntityDeleted);
                return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(colaboradorEntityDeleted));
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_DELETE, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    private ColaboradoresEntity getEntityDelete(ColaboradoresEntity colaboradoresEntity) {
        colaboradoresEntity.setStatus(Constantes.STATUS_INACTIVE);
        colaboradoresEntity.setDateDelete(new Timestamp(System.currentTimeMillis()));
        colaboradoresEntity.setUserDelete(Constantes.AUDIT_ADMIN);
        return colaboradoresEntity;
    }

    @Override
    public ResponseBase listarColaboradores() {
        List<ColaboradoresEntity> colaboradorEntityList = colaboradoresRepository.findAll();
        if (!colaboradorEntityList.isEmpty()) {
            colaboradorEntityList.sort(Comparator.comparingInt(ColaboradoresEntity::getColaboradorId));
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(colaboradorEntityList));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ZERO_ROWS, Optional.empty());
        }
    }
}
