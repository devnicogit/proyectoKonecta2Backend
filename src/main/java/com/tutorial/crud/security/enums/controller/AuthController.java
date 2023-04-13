package com.tutorial.crud.security.enums.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.security.entity.dto.JwtDto;
import com.tutorial.crud.security.entity.dto.LoginUsuario;
import com.tutorial.crud.security.entity.dto.NuevoUsuario;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.dto.RolDto;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.jwt.JwtProvider;
import com.tutorial.crud.security.repository.RolRepository;
import com.tutorial.crud.security.service.AsesorService;
import com.tutorial.crud.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    /*@Autowired
    UsuarioService usuarioService;*/

    @Autowired
    RolRepository rolRepository;

    @Autowired
    AsesorService asesorService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(asesorService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(asesorService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);


        Asesor asesor =
                new Asesor(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(),nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        // asignar roles
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        for (Rol rol : nuevoUsuario.getRoles()) {
            if (rol.getRolNombre().equals(RolNombre.ROLE_ADMIN)) {
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            }
        }

        asesor.setRoles(roles);
        asesorService.save(asesor);
        return new ResponseEntity(new Mensaje("Asesor guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> listarRoles() {
        List<Rol> roles = rolService.findAll();
        return new ResponseEntity<List<Rol>>(roles, HttpStatus.OK);
    }

    @GetMapping("/asesores")
    public ResponseEntity<List<Asesor>> listarAsesores() {
        List<Asesor> asesores = asesorService.findAll();
        return new ResponseEntity(asesores, HttpStatus.OK);
    }

    @GetMapping("/asesores/{nombreUsuario}")
    public ResponseEntity<Asesor> getAsesorByNombreUsuario(@PathVariable String nombreUsuario) {
        Optional<Asesor> asesor = asesorService.getByNombreUsuario(nombreUsuario);

        if (asesor.isPresent()) {
            return ResponseEntity.ok(asesor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
