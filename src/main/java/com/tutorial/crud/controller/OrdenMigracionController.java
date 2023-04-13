package com.tutorial.crud.controller;


import com.tutorial.crud.dto.OrdenMigracionDto;
import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.security.service.AsesorService;
import com.tutorial.crud.service.DetalleOrdenMigracionService;
import com.tutorial.crud.service.OrdenMigracionService;
import com.tutorial.crud.service.PlanPostpagoService;
import com.tutorial.crud.service.TelefonoService;
import com.tutorial.crud.swagger.entity.DetalleOrdenMigracion;
import com.tutorial.crud.swagger.entity.OrdenMigracion;
import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.swagger.entity.Telefono;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ordenmigracion", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class OrdenMigracionController {

    @Autowired
    private OrdenMigracionService ordenMigracionService;

    @Autowired
    private AsesorService asesorService;

    @Autowired
    private PlanPostpagoService planPostpagoService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private DetalleOrdenMigracionService detalleOrdenMigracionService;

    @ApiOperation("Muestra una lista de orden de migración")
    @GetMapping("/lista")
    public ResponseEntity<List<OrdenMigracion>> list(){
        List<OrdenMigracion> list = ordenMigracionService.findAll();
        return new ResponseEntity<List<OrdenMigracion>>(list, HttpStatus.OK);
    }

    @ApiOperation("Muestra una lista de detalle de orden de migración")
    @GetMapping("/detalle/lista")
    public ResponseEntity<List<DetalleOrdenMigracion>> listarDetalle(){
        List<DetalleOrdenMigracion> list = detalleOrdenMigracionService.findAll();
        return new ResponseEntity<List<DetalleOrdenMigracion>>(list, HttpStatus.OK);
    }

   /*@GetMapping("/lista")
    public ResponseEntity<List<OrdenMigracion>> list() {
        List<OrdenMigracion> list = ordenMigracionService.findAllWithTelefono();
        for (OrdenMigracion orden : list) {
            Telefono telefono = orden.getTelefono();
            orden.setTelefono(telefono); // agrega el objeto Telefono completo a la OrdenMigracion
        }
        return ResponseEntity.ok(list);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Telefono> getTelefonoById(@PathVariable Long id) {
        Telefono telefono = telefonoService.findById(id);
        return ResponseEntity.ok(telefono);
    }


    /*@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> guardarOrdenMigracion(
            @RequestBody  Long telefono,
            @RequestBody  Long asesor,
            @RequestBody  Long plan,
            @RequestBody  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha
    ) {
        Optional<Telefono> telefonoOptional  = telefonoService.findByIds(telefono);
        if (!telefonoOptional.isPresent()) {
            return ResponseEntity.badRequest().body("El teléfono con ID " + telefono + " no existe.");
        }

        Optional<Asesor> asesorOptional = asesorService.findById(asesor);
        if (!asesorOptional.isPresent()) {
            return ResponseEntity.badRequest().body("El asesor con ID " + asesor + " no existe.");
        }

        Optional<PlanPostpago> planOptional = planPostpagoService.findByIds(plan);
        if (!planOptional.isPresent()) {
            return ResponseEntity.badRequest().body("El plan con ID " + plan + " no existe.");
        }

        OrdenMigracion ordenMigracion = new OrdenMigracion();
        ordenMigracion.setTelefono(telefonoOptional.get());
        ordenMigracion.setAsesor(asesorOptional.get());
        ordenMigracion.setPlan(planOptional.get());
        ordenMigracion.setFecha(fecha);

        OrdenMigracion nuevaOrdenMigracion = ordenMigracionService.save(ordenMigracion);

        return ResponseEntity.ok(nuevaOrdenMigracion);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdenMigracionDto> guardarOrdenMigracion(
            @RequestBody OrdenMigracionDto ordenMigracionDto
    ) {
        Long telefono = ordenMigracionDto.getTelefono();
        Long asesor = ordenMigracionDto.getAsesor();
        Long plan = ordenMigracionDto.getPlan();
        LocalDate fecha = ordenMigracionDto.getFecha();

        Optional<Telefono> telefonoOptional  = telefonoService.findByIds(telefono);
        if (!telefonoOptional.isPresent()) {
            //return ResponseEntity.badRequest().body("El teléfono con ID " + telefono + " no existe.");
            //return ResponseEntity.badRequest().body(new OrdenMigracionDto("El teléfono con ID " + telefono + " no existe."));
            return ResponseEntity.notFound().build();

        }

        Optional<Asesor> asesorOptional = asesorService.findById(asesor);
        if (!asesorOptional.isPresent()) {
            //return ResponseEntity.badRequest().body("El asesor con ID " + asesor + " no existe.");
           // return ResponseEntity.badRequest().body(new OrdenMigracionDto("El asesor con ID " + asesor + " no existe."));
            return ResponseEntity.notFound().build();

        }

        Optional<PlanPostpago> planOptional = planPostpagoService.findByIds(plan);
        if (!planOptional.isPresent()) {
            //return ResponseEntity.badRequest().body("El plan con ID " + plan + " no existe.");
            //return ResponseEntity.badRequest().body(new OrdenMigracionDto("El plan con ID " + plan + " no existe."));
            return ResponseEntity.notFound().build();
        }

        OrdenMigracion ordenMigracion = new OrdenMigracion();
        ordenMigracion.setTelefono(telefonoOptional.get());
        ordenMigracion.setAsesor(asesorOptional.get());
        ordenMigracion.setPlan(planOptional.get());
        ordenMigracion.setFecha(fecha);

        /*OrdenMigracion nuevaOrdenMigracion = ordenMigracionService.save(ordenMigracion);
        return ResponseEntity.ok(nuevaOrdenMigracion);*/

        OrdenMigracion nuevaOrdenMigracion = ordenMigracionService.save(ordenMigracion);

        DetalleOrdenMigracion nuevoDetalleOrdenMigracion = new DetalleOrdenMigracion();
        nuevoDetalleOrdenMigracion.setOrdenMigracion(nuevaOrdenMigracion);

        nuevoDetalleOrdenMigracion.setCaracteristicasPlan("Características del plan");
        System.out.println("Valor de detallesAsesor: " + asesorOptional.get().getNombreUsuario());
        nuevoDetalleOrdenMigracion.setDetallesAsesor(asesorOptional.get().getNombreUsuario());
        DetalleOrdenMigracion detalleGuardado = detalleOrdenMigracionService.save(nuevoDetalleOrdenMigracion);

        /*nuevaOrdenMigracion.getDetallesOrdenMigracion().add(detalleGuardado);
        nuevaOrdenMigracion = ordenMigracionService.save(nuevaOrdenMigracion);*/

        Telefono telefono1 = nuevaOrdenMigracion.getTelefono();
        PlanPostpago nuevoPlan = planPostpagoService.findByIds(plan).orElseThrow(() -> new RuntimeException("El plan no existe"));
        telefono1.setPlan(nuevoPlan);
        telefonoService.save(telefono1);
        OrdenMigracionDto successDto = new OrdenMigracionDto(nuevaOrdenMigracion);
        return ResponseEntity.ok(successDto);
    }



}
