package com.blh.gestionrrhh.service.impl;

import com.blh.gestionrrhh.agreggates.constants.Constantes;
import com.blh.gestionrrhh.agreggates.request.RequestEmpresa;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.entity.EmpresaEntity;
import com.blh.gestionrrhh.repository.EmpresaRepository;
import com.blh.gestionrrhh.service.EmpresaService;
import com.blh.gestionrrhh.util.EmpresaValidation;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaValidation empresaValidation;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EmpresaValidation empresaValidation) {
        this.empresaRepository = empresaRepository;
        this.empresaValidation = empresaValidation;
    }

    @Override
    public ResponseBase actualizarEmpresaPorId(int id, RequestEmpresa requestEmpresa) {
        boolean validationInput = empresaValidation.validateInput(requestEmpresa);
        if (validationInput) {
            if (empresaRepository.existsById(id)) {
                Optional<EmpresaEntity> empresaEntityToUpdate = empresaRepository.findById(id);
                if (empresaEntityToUpdate.isPresent()) {
                    EmpresaEntity empresaEntityUpdated = getEntityUpdate(requestEmpresa, empresaEntityToUpdate.get());
                    empresaRepository.save(empresaEntityUpdated);
                    return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(empresaEntityUpdated));
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

    private EmpresaEntity getEntityUpdate(RequestEmpresa requestEmpresa, EmpresaEntity empresaEntity) {
        empresaEntity.setDireccion(requestEmpresa.getDireccion());
        empresaEntity.setNombre(requestEmpresa.getNombre());
        empresaEntity.setRuc(requestEmpresa.getRuc());
        empresaEntity.setTelefono(requestEmpresa.getTelefono());
        empresaEntity.setDateModify(new Timestamp(System.currentTimeMillis()));
        empresaEntity.setUserModify(Constantes.AUDIT_ADMIN);
        return empresaEntity;
    }

    @Override
    public ResponseBase buscarEmpresaPorId(int id) {
        if (empresaRepository.existsById(id)) {
            Optional<EmpresaEntity> empresaEntityToFind = empresaRepository.findById(id);
            if(empresaEntityToFind.isPresent()) {
                return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, empresaEntityToFind);
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_FIND, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    @Override
    public ResponseBase crearEmpresa(RequestEmpresa requestEmpresa) {
        boolean inputValidation = empresaValidation.validateInput(requestEmpresa);
        if (inputValidation) {
            EmpresaEntity empresaEntityToCreate = getEntityCreate(requestEmpresa);
            empresaRepository.save(empresaEntityToCreate);
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(empresaEntityToCreate));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
    }

    private EmpresaEntity getEntityCreate(RequestEmpresa requestEmpresa) {
        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity.setDireccion(requestEmpresa.getDireccion());
        empresaEntity.setNombre(requestEmpresa.getNombre());
        empresaEntity.setRuc(requestEmpresa.getRuc());
        empresaEntity.setTelefono(requestEmpresa.getTelefono());
        empresaEntity.setDateCreate(new Timestamp(System.currentTimeMillis()));
        empresaEntity.setUserCreate(Constantes.AUDIT_ADMIN);
        empresaEntity.setStatus(Constantes.STATUS_ACTIVE);
        return empresaEntity;
    }

    @Override
    public ResponseBase eliminarEmpresaPorId(int id) {
        if (empresaRepository.existsById(id)) {
            Optional<EmpresaEntity> empresaEntityToDelete = empresaRepository.findById(id);
            if (empresaEntityToDelete.isPresent()) {
                EmpresaEntity empresaEntityDeleted = getEntityDelete(empresaEntityToDelete.get());
                empresaRepository.save(empresaEntityDeleted);
                return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(empresaEntityDeleted));
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_DELETE, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    private EmpresaEntity getEntityDelete(EmpresaEntity empresaEntity) {
        empresaEntity.setStatus(Constantes.STATUS_INACTIVE);
        empresaEntity.setDateDelete(new Timestamp(System.currentTimeMillis()));
        empresaEntity.setUserDelete(Constantes.AUDIT_ADMIN);
        return empresaEntity;
    }

    @Override
    public ResponseBase listarEmpresas() {
        List<EmpresaEntity> empresaEntityList = empresaRepository.findAll();
        if (!empresaEntityList.isEmpty()) {
            empresaEntityList.sort(Comparator.comparingInt(EmpresaEntity::getEmpresaId));
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(empresaEntityList));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ZERO_ROWS, Optional.empty());
        }
    }
}
