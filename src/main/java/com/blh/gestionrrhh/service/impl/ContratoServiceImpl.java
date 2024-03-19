package com.blh.gestionrrhh.service.impl;

import com.blh.gestionrrhh.agreggates.constants.Constantes;
import com.blh.gestionrrhh.agreggates.request.RequestContrato;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.entity.ColaboradoresEntity;
import com.blh.gestionrrhh.entity.ContratoEntity;
import com.blh.gestionrrhh.entity.EmpresaEntity;
import com.blh.gestionrrhh.repository.ColaboradoresRepository;
import com.blh.gestionrrhh.repository.ContratoRepository;
import com.blh.gestionrrhh.repository.EmpresaRepository;
import com.blh.gestionrrhh.service.ContratoService;
import com.blh.gestionrrhh.util.ContratoValidation;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements ContratoService {
    private final ColaboradoresRepository colaboradoresRepository;
    private final ContratoRepository contratoRepository;
    private final ContratoValidation contratoValidation;
    private final EmpresaRepository empresaRepository;

    public ContratoServiceImpl(ColaboradoresRepository colaboradoresRepository, ContratoRepository contratoRepository,
                               ContratoValidation contratoValidation, EmpresaRepository empresaRepository) {
        this.colaboradoresRepository = colaboradoresRepository;
        this.contratoRepository = contratoRepository;
        this.contratoValidation = contratoValidation;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public ResponseBase actualizarContratoPorId(int id, RequestContrato requestContrato) {
        boolean validarInput = contratoValidation.validateInput(requestContrato);
        if (validarInput) {
            if (contratoRepository.existsById(id)) {
                Optional<ContratoEntity> contratoEntityToUpdate = contratoRepository.findById(id);
                if (contratoEntityToUpdate.isPresent()) {
                    ContratoEntity contratoEntityUpdated = getEntityUpdate(requestContrato, contratoEntityToUpdate.get());
                    contratoRepository.save(contratoEntityUpdated);
                    return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(contratoEntityUpdated));
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

    private ContratoEntity getEntityUpdate(RequestContrato requestContrato, ContratoEntity contratoEntity) {
        contratoEntity.setBeneficios(requestContrato.getBeneficios());
        contratoEntity.setColaboradoresEntity(getColaborador(requestContrato.getColaboradorId()));
        contratoEntity.setDescripcion(requestContrato.getDescripcion());
        contratoEntity.setDuracion(requestContrato.getDuracion());
        contratoEntity.setEmpresaEntity(getEmpresa(requestContrato.getEmpresaId()));
        contratoEntity.setFunciones(requestContrato.getFunciones());
        contratoEntity.setModalidad(requestContrato.getModalidad());
        contratoEntity.setRemuneracion(requestContrato.getRemuneracion());
        contratoEntity.setTipoContrato(requestContrato.getTipoContrato());
        contratoEntity.setDateModify(new Timestamp(System.currentTimeMillis()));
        contratoEntity.setUserModify(Constantes.AUDIT_ADMIN);
        return contratoEntity;
    }

    @Override
    public ResponseBase buscarContratoPorId(int id) {
        if (contratoRepository.existsById(id)) {
            Optional<ContratoEntity> contratoEntityToFind = contratoRepository.findById(id);
            if(contratoEntityToFind.isPresent()) {
                return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, contratoEntityToFind);
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_FIND, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    @Override
    public ResponseBase crearContrato(RequestContrato requestContrato) {
        boolean inputValidation = contratoValidation.validateInput(requestContrato);
        if (inputValidation) {
            ContratoEntity contratoEntityToCreate = getEntityCreate(requestContrato);
            contratoRepository.save(contratoEntityToCreate);
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(contratoEntityToCreate));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_DATA_NOT_VALID, Optional.empty());
        }
    }

    private ContratoEntity getEntityCreate(RequestContrato requestContrato) {
        ContratoEntity contratoEntityCreate = new ContratoEntity();
        contratoEntityCreate.setBeneficios(requestContrato.getBeneficios());
        contratoEntityCreate.setColaboradoresEntity(getColaborador(requestContrato.getColaboradorId()));
        contratoEntityCreate.setDescripcion(requestContrato.getDescripcion());
        contratoEntityCreate.setDuracion(requestContrato.getDuracion());
        contratoEntityCreate.setEmpresaEntity(getEmpresa(requestContrato.getEmpresaId()));
        contratoEntityCreate.setFunciones(requestContrato.getFunciones());
        contratoEntityCreate.setModalidad(requestContrato.getModalidad());
        contratoEntityCreate.setRemuneracion(requestContrato.getRemuneracion());
        contratoEntityCreate.setTipoContrato(requestContrato.getTipoContrato());
        contratoEntityCreate.setDateCreate(new Timestamp(System.currentTimeMillis()));
        contratoEntityCreate.setUserCreate(Constantes.AUDIT_ADMIN);
        contratoEntityCreate.setStatus(Constantes.STATUS_ACTIVE);
        return contratoEntityCreate;
    }

    private EmpresaEntity getEmpresa(int empresaId) {
        if (contratoRepository.existsById(empresaId)) {
            Optional<EmpresaEntity> empresaEntity = empresaRepository.findById(empresaId);
            return empresaEntity.orElse(null);
        } else {
            return null;
        }
    }

    private ColaboradoresEntity getColaborador(int colaboradorId) {
        if (colaboradoresRepository.existsById(colaboradorId)) {
            Optional<ColaboradoresEntity> colaboradoresEntity = colaboradoresRepository.findById(colaboradorId);
            return colaboradoresEntity.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public ResponseBase eliminarContratoPorId(int id) {
        if (contratoRepository.existsById(id)) {
            Optional<ContratoEntity> contratoEntityToDelete = contratoRepository.findById(id);
            if(contratoEntityToDelete.isPresent()) {
                ContratoEntity contratoEntity = getEntityDelete(contratoEntityToDelete.get());
                contratoRepository.save(contratoEntity);
                return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, contratoEntityToDelete);
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_NOT_FIND, Optional.empty());
            }
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_NOT_FOUND_ID, Optional.empty());
        }
    }

    private ContratoEntity getEntityDelete(ContratoEntity contratoEntity) {
        contratoEntity.setStatus(Constantes.STATUS_INACTIVE);
        contratoEntity.setUserDelete(Constantes.AUDIT_ADMIN);
        contratoEntity.setDateDelete(new Timestamp(System.currentTimeMillis()));
        return contratoEntity;
    }

    @Override
    public ResponseBase listarContratos() {
        List<ContratoEntity> contratoEntityList = contratoRepository.findAll();
        if (!contratoEntityList.isEmpty()) {
            contratoEntityList.sort(Comparator.comparingInt(ContratoEntity::getContratoId));
            return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(contratoEntityList));
        } else {
            return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ZERO_ROWS, Optional.empty());
        }
    }
}
