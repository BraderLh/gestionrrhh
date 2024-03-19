package com.blh.gestionrrhh.service.impl;

import com.blh.gestionrrhh.agreggates.constants.Constantes;
import com.blh.gestionrrhh.agreggates.response.ResponseBase;
import com.blh.gestionrrhh.entity.UsuariosEntity;
import com.blh.gestionrrhh.repository.UsuariosRepository;
import com.blh.gestionrrhh.security.PersonDetailService;
import com.blh.gestionrrhh.security.jwt.JwtUtil;
import com.blh.gestionrrhh.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UsuariosServiceImpl implements UsuariosService {
    private final UsuariosRepository usuariosRepository;
    private final AuthenticationManager authenticationManager;
    private final PersonDetailService personDetailService;
    private final JwtUtil jwtUtil;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository, AuthenticationManager authenticationManager, PersonDetailService personDetailService, JwtUtil jwtUtil) {
        this.usuariosRepository = usuariosRepository;
        this.authenticationManager = authenticationManager;
        this.personDetailService = personDetailService;
        this.jwtUtil = jwtUtil;
    }

    private UsuariosEntity getUsersFromMap(Map<String, String> requestMap){
        UsuariosEntity usersEntity = new UsuariosEntity();
        usersEntity.setEmail(requestMap.get("email"));
        usersEntity.setPassword(requestMap.get("password"));
        usersEntity.setStatus(Constantes.STATUS_ACTIVE);
        usersEntity.setRol(Constantes.AUDIT_USER);
        usersEntity.setUserCreate(Constantes.AUDIT_ADMIN);
        usersEntity.setDateCreate(new Timestamp(System.currentTimeMillis()));
        return usersEntity;
    }

    @Override
    public ResponseBase registrar(Map<String, String> requestMap) {
        log.info("Signing up a user: " + requestMap.toString());
        UsuariosEntity usersEntity = getUsersFromMap(requestMap);
        usuariosRepository.save(usersEntity);
        log.info("Ending signing up");
        return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(usersEntity));
    }

    @Override
    public ResponseBase iniciar(Map<String, String> requestMap) {
        log.info("Init login");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),
                            requestMap.get("password")));
            if (authentication.isAuthenticated()) {
                if (personDetailService.getUsers().getStatus() == Constantes.STATUS_ACTIVE) {
                    String token = jwtUtil.generateToken(personDetailService.getUsers().getEmail(), personDetailService.getUsers().getRol());
                    return new ResponseBase(Constantes.CODE_SUCCESS, Constantes.MESS_SUCCESS, Optional.of(token));
                }
            } else {
                return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR_LOGIN, Optional.empty());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseBase(Constantes.CODE_ERROR, Constantes.MESS_ERROR, Optional.of("Invalid or incorrect password"));
    }

    @Override
    public ResponseBase obtenerUsuarios() {
        return null;
    }

    @Override
    public ResponseBase obtenerUsuarioPorEmail(String email) {
        return null;
    }

    @Override
    public ResponseBase eliminarUsuarioPorId(int id) {
        return null;
    }
}
