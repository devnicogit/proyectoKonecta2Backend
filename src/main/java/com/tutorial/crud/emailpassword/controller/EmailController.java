package com.tutorial.crud.emailpassword.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.emailpassword.dto.ChangePasswordDTO;
import com.tutorial.crud.emailpassword.dto.EmailValuesDTO;
import com.tutorial.crud.emailpassword.service.EmailService;
import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.security.entity.dto.NuevoUsuario;
import com.tutorial.crud.security.service.AsesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/email-password")
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    /*@Autowired
    UsuarioService usuarioService;*/

    @Autowired
    AsesorService asesorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private static final String subject = "Cambio de Contraseña";

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) {
        Optional<Asesor> asesorOpt = asesorService.getByNombreUsuarioOrEmail(dto.getMailTo());
        if(!asesorOpt.isPresent())
            return new ResponseEntity(new Mensaje("No existe ningún Asesor con esas credenciales"), HttpStatus.NOT_FOUND);
        Asesor asesor = asesorOpt.get();
        dto.setMailFrom(mailFrom);
        dto.setMailTo(asesor.getEmail());
        dto.setSubject(subject);
        dto.setUserName(asesor.getNombreUsuario());
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        asesor.setTokenPassword(tokenPassword);
        asesorService.save(asesor);
        emailService.sendEmail(dto);
        return new ResponseEntity(new Mensaje("Te hemos enviado un correo"), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
        Optional<Asesor> asesorOpt = asesorService.getByTokenPassword(dto.getTokenPassword());
        if(!asesorOpt.isPresent())
            return new ResponseEntity(new Mensaje("No existe ningún asesor con esas credenciales"), HttpStatus.NOT_FOUND);
        Asesor asesor = asesorOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        asesor.setPassword(newPassword);
        asesor.setTokenPassword(null);
        asesorService.save(asesor);
        return new ResponseEntity(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
    }


}
