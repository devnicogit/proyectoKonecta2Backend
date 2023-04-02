package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.TelefonoDto;
import com.tutorial.crud.service.ClienteService;
import com.tutorial.crud.service.PlanPostpagoService;
import com.tutorial.crud.service.TelefonoService;
import com.tutorial.crud.service.TipoClienteService;
import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.swagger.entity.Telefono;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/telefono")
@CrossOrigin(origins = "*")
public class TelefonoController {


    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PlanPostpagoService planPostpagoService;

    @ApiOperation("Muestra una lista de telefonos")
    @GetMapping("/lista")
    public ResponseEntity<List<Telefono>> list(){
        List<Telefono> list = telefonoService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Telefono> getTelefonoById(@PathVariable Long id) {
        Telefono telefono = telefonoService.findById(id);
        return ResponseEntity.ok(telefono);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createTelefono(@RequestBody TelefonoDto telefonoDto) {
        Cliente cliente = clienteService.findById(telefonoDto.getCliente());
        telefonoDto.setCliente(telefonoDto.getCliente());
        PlanPostpago planPostpago = planPostpagoService.findById(telefonoDto.getPlanPostpago());
        telefonoDto.setPlanPostpago(telefonoDto.getPlanPostpago());

        Telefono telefono = new Telefono(telefonoDto.getNumero(),planPostpago,cliente);
        telefonoService.save(telefono);
        return new ResponseEntity<>(new Mensaje("Teléfono Creado"), HttpStatus.OK);

    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Telefono> updateTelefono(@PathVariable Long id, @Valid @RequestBody Telefono telefono) {

        PlanPostpago planPostpago = planPostpagoService.findById(telefono.getPlan().getPlanId());
        Cliente cliente = clienteService.findById(telefono.getCliente().getClienteId());

        // Verificar si los objetos son nulos antes de acceder a sus propiedades
        if (planPostpago == null) {
            // Manejar el caso en que planPostpago sea nulo
            return ResponseEntity.badRequest().build();
        }

        if (cliente == null) {
            // Manejar el caso en que cliente sea nulo
            return ResponseEntity.badRequest().build();
        }
        telefono.setPlan(planPostpago);
        telefono.setCliente(cliente);


        Telefono updatedTelefono = telefonoService.update(id, telefono);
        return ResponseEntity.ok(updatedTelefono);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTelefono(@PathVariable Long id) {
        telefonoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
