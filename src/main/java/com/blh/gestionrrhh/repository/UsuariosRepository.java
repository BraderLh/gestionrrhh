package com.blh.gestionrrhh.repository;

import com.blh.gestionrrhh.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Integer> {
    UsuariosEntity buscarPorEmail(@Param("email") String email);
}
