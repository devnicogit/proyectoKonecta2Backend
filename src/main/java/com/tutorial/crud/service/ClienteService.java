package com.tutorial.crud.service;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.Telefono;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();

    Cliente findById(Long id);

    Cliente save(Cliente cliente);


    Cliente update(Long id, ClienteDto clienteDto);

    List<Telefono> findByCliente(Cliente cliente);

    void actualizarTiposCliente(Cliente cliente);

    void actualizarTiposClienteSiNecesario(Cliente cliente, Telefono telefonoOriginal);

    void delete(Long id);
}
