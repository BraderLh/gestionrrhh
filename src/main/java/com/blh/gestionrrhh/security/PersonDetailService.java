package com.blh.gestionrrhh.security;

import com.blh.gestionrrhh.entity.UsuariosEntity;
import com.blh.gestionrrhh.repository.UsuariosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class PersonDetailService implements UserDetailsService {
    private final UsuariosRepository usuariosRepository;
    UsuariosEntity usuariosEntity;

    public PersonDetailService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Login as loadUserByUsername: " + username);
        usuariosEntity = usuariosRepository.buscarPorEmail(username);
        if (!Objects.isNull(usuariosEntity)) {
            return new User(usuariosEntity.getEmail(), usuariosEntity.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UsuariosEntity getUsers() {
        return usuariosEntity;
    }
}
