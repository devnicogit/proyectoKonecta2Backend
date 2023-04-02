package com.tutorial.crud.controller;

import com.tutorial.crud.swagger.entity.TipoCliente;
import com.tutorial.crud.service.TipoClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tipocliente")
@CrossOrigin(origins = "*")
public class TipoClienteController {

    @Autowired
    private TipoClienteService tipoClienteService;

    @ApiOperation("Muestra una lista de productos")
    @GetMapping("/lista")
    public ResponseEntity<List<TipoCliente>> list(){
        List<TipoCliente> list = tipoClienteService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCliente> getTipoClienteById(@PathVariable Long id) {
        TipoCliente tipoCliente = tipoClienteService.findById(id);
        return ResponseEntity.ok(tipoCliente);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoCliente> createTipoCliente(@Valid @RequestBody TipoCliente tipoCliente) {
        TipoCliente createdTipoCliente = tipoClienteService.save(tipoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTipoCliente);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoCliente> updateTipoCliente(@PathVariable Long id, @Valid @RequestBody TipoCliente tipoCliente) {
        TipoCliente updatedTipoCliente = tipoClienteService.update(id,tipoCliente);
        return ResponseEntity.ok(updatedTipoCliente);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTipoCliente(@PathVariable Long id) {
        tipoClienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
