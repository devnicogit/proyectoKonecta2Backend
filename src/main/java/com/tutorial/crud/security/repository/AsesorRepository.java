package com.tutorial.crud.security.repository;


import com.tutorial.crud.security.entity.Asesor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Long> {

    Optional<Asesor> findById(Long id);
    /*Optional<Usuario> findById(Integer id);*/
    Optional<Asesor> findByNombreUsuario(String nombreUsuario);
    Optional<Asesor> findByNombreUsuarioOrEmail(String nombreUsuario, String email);
    Optional<Asesor> findByTokenPassword(String tokenPassword);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);




}
