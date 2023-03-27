package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.TipoClienteDto;
import com.tutorial.crud.entity.Cliente;
import com.tutorial.crud.entity.PlanPostpago;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.entity.TipoCliente;
import com.tutorial.crud.service.ClienteService;
import com.tutorial.crud.service.PlanPostpagoService;
import com.tutorial.crud.service.TipoClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @PostMapping("/create")
    public ResponseEntity<?> createCliente(@RequestBody ClienteDto clienteDto) {
        TipoCliente tipoCliente = tipoClienteService.findById(clienteDto.getTipoCliente());
        clienteDto.setTipoCliente(clienteDto.getTipoCliente());
        PlanPostpago planPostpago = planPostpagoService.findById(clienteDto.getPlanPostpago());
        clienteDto.setPlanPostpago(clienteDto.getPlanPostpago());

        /*if (cliente.getClienteId() == null) {
            // Generar un nuevo id
            //cliente.setClienteId(0);
            cliente = clienteService.save(cliente);
        } else {
            // Actualizar un registro existente
            Cliente existingCliente = clienteService.findById(cliente.getClienteId());
            if (existingCliente == null) {
                return ResponseEntity.notFound().build();
            }
            cliente = clienteService.save(cliente);
        }*/
        Cliente cliente = new Cliente(clienteDto.getNombre(),clienteDto.getApellido(),clienteDto.getDireccion(),clienteDto.getTelefono(),tipoCliente,planPostpago);
        clienteService.save(cliente);
        return new ResponseEntity<>(new Mensaje("Cliente Creado"), HttpStatus.OK);
        //Cliente createdCliente = clienteService.save(cliente);

        //return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);

    }

    /*@PostMapping("/create")
    public ResponseEntity<ClienteDto> createCliente(@Valid @RequestBody Cliente cliente) {
        TipoCliente tipoCliente = tipoClienteService.findById(cliente.getTipoCliente().getTipoId());
        cliente.setTipoCliente(tipoCliente);
        PlanPostpago planPostpago = planPostpagoService.findById(cliente.getPlanPostpago().getPlanId());
        cliente.setPlanPostpago(planPostpago);
        ClienteDto clienteDto = new ClienteDto(cliente);
        ClienteDto createdCliente = clienteService.save(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }*/

    /*@PostMapping("/create")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDTO) {
        TipoCliente tipoClienteDto = tipoClienteService.findById(clienteDTO.getTipoClienteDto().getTipoId());
        ClienteDto createdCliente = clienteService.createCliente(clienteDTO);
        return new ResponseEntity<ClienteDto>(createdCliente, HttpStatus.CREATED);
    }*/



    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {



       /* TipoCliente tipoCliente = null;
        PlanPostpago planPostpago = null;

        if(cliente.getTipoCliente() != null) {
            tipoCliente = tipoClienteService.findById(cliente.getTipoCliente().getTipoId());
            if(tipoCliente == null) {
                throw new IllegalArgumentException("Tipo de cliente no encontrado");
            }
        }
        if(cliente.getPlanPostpago() != null) {
            planPostpago = planPostpagoService.findById(cliente.getPlanPostpago().getPlanId());
            if(planPostpago == null) {
                throw new IllegalArgumentException("Plan no encontrado");
            }
        }
        cliente.setTipoCliente(tipoCliente);
        cliente.setPlanPostpago(planPostpago);*/


        PlanPostpago planPostpago = planPostpagoService.findById(cliente.getPlanPostpago().getPlanId());
        TipoCliente tipoCliente = tipoClienteService.findById(cliente.getTipoCliente().getTipoId());

        // Verificar si los objetos son nulos antes de acceder a sus propiedades
        if (planPostpago == null) {
            // Manejar el caso en que planPostpago sea nulo
            return ResponseEntity.badRequest().build();
        }

        if (tipoCliente == null) {
            // Manejar el caso en que tipoCliente sea nulo
            return ResponseEntity.badRequest().build();
        }
        cliente.setPlanPostpago(planPostpago);
        cliente.setTipoCliente(tipoCliente);


        /*Cliente clienteEncontrado = clienteService.findById(id);

        if (cliente.getTipoCliente() != null) {
            TipoCliente tipoCliente = tipoClienteService.findById(cliente.getTipoCliente().getTipoId());
            clienteEncontrado.setTipoCliente(tipoCliente);
        }

        if (cliente.getPlanPostpago() != null) {
            PlanPostpago planPostpago = planPostpagoService.findById(cliente.getPlanPostpago().getPlanId());
            clienteEncontrado.setPlanPostpago(planPostpago);
        }

        clienteEncontrado.setNombre(cliente.getNombre());
        clienteEncontrado.setApellido(cliente.getApellido());
        clienteEncontrado.setTelefono(cliente.getTelefono());
        clienteEncontrado.setDireccion(cliente.getDireccion());*/


        Cliente updatedCliente = clienteService.update(id, cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("/{id}/ventas")
    public ResponseEntity<List<Venta>> getVentasByClienteId(@PathVariable Long id) {
        List<Venta> ventas = clienteService.getVentasByClienteId(id);
        return ResponseEntity.ok(ventas);
    }*/
}
