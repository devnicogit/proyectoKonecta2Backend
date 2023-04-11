package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.TelefonoDto;
import com.tutorial.crud.repository.TipoClienteRepository;
import com.tutorial.crud.service.ClienteService;
import com.tutorial.crud.service.PlanPostpagoService;
import com.tutorial.crud.service.TelefonoService;
import com.tutorial.crud.service.TipoClienteService;
import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.swagger.entity.Telefono;
import com.tutorial.crud.swagger.entity.TipoCliente;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @Autowired
    private TipoClienteService tipoClienteService;

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
        PlanPostpago planPostpago = planPostpagoService.findById(telefonoDto.getPlanPostpago());

        //telefonoDto.setCliente(cliente.getClienteId());
        //telefonoDto.setPlanPostpago(planPostpago.getPlanId());

        Telefono telefono = new Telefono(telefonoDto.getNumero(),planPostpago,cliente);
        telefonoService.save(telefono);

        // Actualizar los tipos de cliente del cliente
        /*Set<TipoCliente> tiposCliente = new HashSet<>();
        if (planPostpago.getPlanId() == 1) {
            // Si el plan es prepago, asigna el tipo 1
            TipoCliente tipoCliente = tipoClienteService.findById(1L);
            tiposCliente.add(tipoCliente);
        } else {
            // Si el plan es postpago, asigna el tipo 2
            TipoCliente tipoCliente = tipoClienteService.findById(2L);
            tiposCliente.add(tipoCliente);
        }
        cliente.setTipoCliente(tiposCliente);
        clienteService.save(cliente);*/
        // Agregar o actualizar el registro en la tabla cliente_tipo_cliente
        // Agregar o actualizar el registro en la tabla cliente_tipo_cliente
        TipoCliente tipoCliente = null;
        if (planPostpago.getPlanId() == 1) {
            // Si el plan es prepago, asigna el tipo 1
            tipoCliente = tipoClienteService.findById(1L);
        } else {
            // Si el plan es postpago, asigna el tipo 2
            tipoCliente = tipoClienteService.findById(2L);
        }

        Set<TipoCliente> tiposCliente = cliente.getTipoCliente();
        tiposCliente.add(tipoCliente);
        cliente.setTipoCliente(tiposCliente);
        clienteService.save(cliente);

        return new ResponseEntity<>(new Mensaje("Teléfono Creado"), HttpStatus.OK);



    }



    /*@PutMapping("/update/{id}")
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
    }*/

    /*@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Telefono> updateTelefono(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Telefono telefono = telefonoService.findById(id);
        if (telefono == null) {
            return ResponseEntity.notFound().build();
        }
        if (updates.containsKey("numero")) {
            telefono.setNumero((String) updates.get("numero"));
        }
        if (updates.containsKey("plan")) {
            LinkedHashMap planMap = (LinkedHashMap) updates.get("plan");
            Long planId = ((Number) planMap.get("planId")).longValue();
            //PlanPostpago planPostpago = planPostpagoService.findById(Long.valueOf((Integer) updates.get("plan")));
            PlanPostpago planPostpago = planPostpagoService.findById(planId);
            if (planPostpago == null) {
                return ResponseEntity.badRequest().build();
            }
            Telefono originalTelefono = new Telefono();
            originalTelefono.setPlan(telefono.getPlan());
            //telefono.setPlan(planPostpago);
            clienteService.actualizarTiposClienteSiNecesario(telefono.getCliente(), originalTelefono);
            telefono.setPlan(planPostpago);
            //clienteService.actualizarTiposCliente(telefono.getCliente());
        }
        if (updates.containsKey("cliente")) {
            LinkedHashMap clienteMap = (LinkedHashMap) updates.get("cliente");
            Long clienteId = ((Number) clienteMap.get("clienteId")).longValue();
            //Cliente cliente = clienteService.findById(Long.valueOf((Integer) updates.get("cliente")));
            Cliente cliente = clienteService.findById(clienteId);
            if (cliente == null) {
                return ResponseEntity.badRequest().build();
            }
            telefono.setCliente(cliente);
        }
        Telefono updatedTelefono = telefonoService.update(id, telefono);
        //clienteService.actualizarTiposCliente(telefono.getCliente());
        return ResponseEntity.ok(updatedTelefono);
    }
*/

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
            // Manejar el caso en que tipoCliente sea nulo
            return ResponseEntity.badRequest().build();
        }
        telefono.setPlan(planPostpago);
        telefono.setCliente(cliente);


        Telefono updatedTelefono = telefonoService.update(id, telefono);
        return ResponseEntity.ok(updatedTelefono);
    }


    /*@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Telefono> updateTelefono(@PathVariable Long id, @RequestBody Telefono telefono) {
        Telefono telefonoActual = telefonoService.findById(id);
        if (telefonoActual == null) {
            return ResponseEntity.notFound().build();
        }
        telefonoActual.setNumero(telefono.getNumero());
        if (telefono.getPlan() != null) {
            PlanPostpago planPostpago = planPostpagoService.findById(telefono.getPlan().getPlanId());
            if (planPostpago == null) {
                return ResponseEntity.badRequest().build();
            }
            Telefono originalTelefono = new Telefono();
            originalTelefono.setPlan(telefonoActual.getPlan());
            clienteService.actualizarTiposClienteSiNecesario(telefonoActual.getCliente(), originalTelefono);
            telefonoActual.setPlan(planPostpago);
        }
        if (telefono.getCliente() != null) {
            Cliente cliente = clienteService.findById(telefono.getCliente().getClienteId());
            if (cliente == null) {
                return ResponseEntity.badRequest().build();
            }
            telefonoActual.setCliente(cliente);
        }
        Telefono updatedTelefono = telefonoService.update(id, telefonoActual);
        return ResponseEntity.ok(updatedTelefono);
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTelefono(@PathVariable Long id) {
        telefonoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
