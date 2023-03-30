package com.tutorial.crud.security.service;

import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.security.repository.AsesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AsesorService {

    @Autowired
    AsesorRepository asesorRepository;

    public Optional<Asesor> getByNombreUsuario(String nombreUsuario){
        return asesorRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Asesor> getByNombreUsuarioOrEmail(String nombreOrEmail){
        return asesorRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public Optional<Asesor> getByTokenPassword(String tokenPassword){
        return asesorRepository.findByTokenPassword(tokenPassword);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return asesorRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return asesorRepository.existsByEmail(email);
    }

    public void save(Asesor asesor){
        asesorRepository.save(asesor);
    }
}
