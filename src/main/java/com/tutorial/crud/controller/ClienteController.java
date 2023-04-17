package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.repository.TipoClienteRepository;
import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.service.ClienteService;
import com.tutorial.crud.service.PlanPostpagoService;
import com.tutorial.crud.service.TipoClienteService;
import com.tutorial.crud.swagger.entity.TipoCliente;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TipoClienteService tipoClienteService;

    @Autowired
    private PlanPostpagoService planPostpagoService;

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @ApiOperation("Muestra una lista de productos")
    @GetMapping("/lista")
    public ResponseEntity<List<Cliente>> list(){
        List<Cliente> list = clienteService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCliente(@Valid @RequestBody ClienteDto clienteDto,  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = new Cliente(clienteDto.getDni(),clienteDto.getNombre(), clienteDto.getApellido(), clienteDto.getDireccion(), clienteDto.getEmail());
        Set<TipoCliente> tiposCliente = new HashSet<>();
        for (Long tipoClienteId : clienteDto.getTipoClienteIds()) {
            TipoCliente tipoCliente = tipoClienteRepository.findById(tipoClienteId)
                    .orElseThrow(() -> new RuntimeException("No se encontró el tipo de cliente con el ID " + tipoClienteId));
            tiposCliente.add(tipoCliente);
        }
        cliente.setTipoCliente(tiposCliente);
        clienteService.save(cliente);

        return new ResponseEntity(new Mensaje("Cliente guardado"), HttpStatus.CREATED);

    }




    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteDto clienteDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }

        ClienteDto clienteDto1 = new ClienteDto(clienteDto.getDni(), clienteDto.getNombre(), clienteDto.getApellido(), clienteDto.getDireccion(), clienteDto.getEmail());
        clienteDto1.setTipoClienteIds(clienteDto.getTipoClienteIds());

        clienteService.update(id, clienteDto1);

        return new ResponseEntity(new Mensaje("Cliente actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
