package com.tutorial.crud.emailpassword.service;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.OrdenMigracionDto;
import com.tutorial.crud.dto.TelefonoDto;
import com.tutorial.crud.emailpassword.dto.EmailValuesDTO;
import com.tutorial.crud.repository.ClienteRepository;
import com.tutorial.crud.swagger.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    ClienteRepository clienteRepository;

    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendEmail(EmailValuesDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("url", urlFront + dto.getTokenPassword());
            context.setVariables(model);
            String htmlText = templateEngine.process("email-template", context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);

            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void sendEmailPDF(EmailValuesDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();

        ClienteDto dto2 = new ClienteDto();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());

            // Agrega el archivo PDF como adjunto
            if (dto.getPdfBytes() != null) {
                ByteArrayResource pdfAttachment = new ByteArrayResource(dto.getPdfBytes());
                System.out.println(Arrays.toString(dto.getPdfBytes()));
                helper.addAttachment("reporte.pdf", pdfAttachment);
            } else {
                throw new RuntimeException("El arreglo de bytes del PDF es nulo");
            }

            // Agrega el cuerpo del mensaje
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("usenombreUsuariorName", dto2.getNombre());
            context.setVariable("nombreUsuario", dto2.getNombre());
            context.setVariable("mensaje", dto.getMessage());
            String htmlContent = templateEngine.process("email-template2", context);
            helper.setText(htmlContent, true);


            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
