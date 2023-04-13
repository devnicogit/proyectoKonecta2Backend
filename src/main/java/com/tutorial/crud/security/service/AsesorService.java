package com.tutorial.crud.security.service;

import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.dto.NuevoUsuario;
import com.tutorial.crud.security.repository.AsesorRepository;
import com.tutorial.crud.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AsesorService {

    @Autowired
    AsesorRepository asesorRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    /*public void save(Asesor asesor){
        asesorRepository.save(asesor);
    }*/

    public void save(NuevoUsuario nuevoUsuario) {
        Asesor asesor = new Asesor();
        asesor.setNombreUsuario(nuevoUsuario.getNombreUsuario());
        asesor.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
        asesor.setEmail(nuevoUsuario.getEmail());
        // otros atributos que quieras asignar al asesor

        Set<Rol> roles = new HashSet<>();
        for (Long rolId : nuevoUsuario.getRolIds()) {
            Rol rol = rolRepository.findById(rolId)
                    .orElseThrow(() -> new RuntimeException("No se encontró el rol con el ID " + rolId));
            roles.add(rol);
        }
        asesor.setRoles(roles);

        asesorRepository.save(asesor);
    }


    public void save(Asesor asesor) {
        asesorRepository.save(asesor);
    }

    public List<Asesor> findAll(){return asesorRepository.findAll();};

    public Optional<Asesor> findById(Long id) {
        return asesorRepository.findById(id);
    }


}
