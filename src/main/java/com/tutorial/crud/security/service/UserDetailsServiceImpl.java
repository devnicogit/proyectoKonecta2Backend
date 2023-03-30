package com.tutorial.crud.security.service;

import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.security.entity.AsesorPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AsesorService asesorService;

    @Override
    public UserDetails loadUserByUsername(String nombreOrEmail) throws UsernameNotFoundException {
        Asesor asesor = asesorService.getByNombreUsuarioOrEmail(nombreOrEmail).get();
        return AsesorPrincipal.build(asesor);
    }
}
