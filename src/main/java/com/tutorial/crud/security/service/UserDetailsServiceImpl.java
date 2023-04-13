package com.tutorial.crud.security.service;

import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.security.entity.AsesorPrincipal;
import com.tutorial.crud.security.repository.AsesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AsesorService asesorService;

    @Autowired
    AsesorRepository asesorRepository;

    @Override
    public UserDetails loadUserByUsername(String nombreOrEmail) throws UsernameNotFoundException {
        Asesor asesor = asesorService.getByNombreUsuarioOrEmail(nombreOrEmail).get();
        return AsesorPrincipal.build(asesor);

    }


}
