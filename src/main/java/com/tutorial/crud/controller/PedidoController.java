package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.PedidoDto;
import com.tutorial.crud.dto.ProductoDto;
import com.tutorial.crud.entity.Pedido;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.repository.UsuarioRepository;
import com.tutorial.crud.security.service.UsuarioService;
import com.tutorial.crud.service.PedidoService;
import com.tutorial.crud.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ProductoService productoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> list(){
        List<Pedido> list = pedidoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @ApiIgnore
    @GetMapping("/detail/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable("id") int id){
        if(!pedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Pedido pedido = pedidoService.getOne(id).get();
        return new ResponseEntity(pedido, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PedidoDto pedidoDto){
        /*if(pedidoDto.getIdped()==0)
            return new ResponseEntity(new Mensaje("El ID del Usuario es obligatorio"), HttpStatus.BAD_REQUEST);*/
        if(pedidoDto.getCantidad()==null || pedidoDto.getCantidad()<0 )
            return new ResponseEntity(new Mensaje("La cantidad debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(pedidoDto.getPrecioUnitario()==null || pedidoDto.getPrecioUnitario()<0 )
            return new ResponseEntity(new Mensaje("El precio unitario debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(pedidoDto.getPrecioTotal()==null || pedidoDto.getPrecioTotal()<0 )
            return new ResponseEntity(new Mensaje("El precio final debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        /*Pedido pedido = new Pedido(pedidoDto.,pedidoDto.getCantidad(),pedidoDto.getPrecioUnitario(),pedidoDto.getPrecioTotal(),pedidoDto.getEstado());*/
        /*PedidoDto ped = new PedidoDto(pedidoDto.getUsuario(),pedidoDto.getCantidad(),pedidoDto.getPrecioUnitario(),pedidoDto.getPrecioTotal(),pedidoDto.getEstado());*/
       /* Set<Producto> productos = new HashSet<>();
        productos.add(productoService.getById();
        Pedido pedido = new Pedido();
        pedido.setProducto(productos);*/
        pedidoService.create(pedidoDto);
        return new ResponseEntity(new Mensaje("Pedido creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody PedidoDto pedidoDto){
        if(!pedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(pedidoDto.getPrecioUnitario()==null || pedidoDto.getPrecioUnitario()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(pedidoDto.getPrecioTotal()==null || pedidoDto.getPrecioTotal()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Pedido pedido = pedidoService.getOne(id).get();
        Optional<Usuario> usuario =  usuarioRepository.findById(pedidoDto.getUsuario());
        pedido.setCantidad(pedidoDto.getCantidad());
        pedido.setPrecioUnitario(pedidoDto.getPrecioUnitario());
        pedido.setPrecioTotal(pedidoDto.getPrecioTotal());
        pedido.setEstado(pedidoDto.getEstado());
        pedidoService.save(pedido);
        return new ResponseEntity(new Mensaje("pedido actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!pedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        pedidoService.delete(id);
        return new ResponseEntity(new Mensaje("Pedido eliminado"), HttpStatus.OK);
    }
}
