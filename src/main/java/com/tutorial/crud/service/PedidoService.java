package com.tutorial.crud.service;

import com.tutorial.crud.dto.PedidoDto;
import com.tutorial.crud.entity.Pedido;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.repository.PedidoRepository;
import com.tutorial.crud.repository.ProductoRepository;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProductoRepository productoRepository;

    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    public boolean existsById(int id) {
        return pedidoRepository.existsById(id);
    }

    public Optional<Pedido> getOne(int id) {
        return pedidoRepository.findById(id);
    }

    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }


    public void create(PedidoDto pedidoDto) {
        Optional<Usuario> usuario =  usuarioRepository.findById(pedidoDto.getUsuario());
        Pedido pedido = new Pedido(usuario.get(),pedidoDto.getCantidad(),pedidoDto.getPrecioUnitario(),pedidoDto.getPrecioTotal(),pedidoDto.getEstado());
        pedido.setProducto(pedidoDto.getProductos().stream().map(producto -> {
            /*Optional<Producto> pro = producto;
            if(pro.getId() > 0){
                pro = productoRepository.findById(pro.getId());
            }
            pro.agregarPedido(pedido);
            return pro;*/
            Producto prod = producto;
            if(prod.getId() > 0){
                prod = productoRepository.findById(prod.getId()).get();
            }
            prod.agregarPedido(pedido);
            return prod;
        }).collect(Collectors.toSet()));
        pedidoRepository.save(pedido);
    }

    public void delete(int id){
        pedidoRepository.deleteById(id);
    }
}
