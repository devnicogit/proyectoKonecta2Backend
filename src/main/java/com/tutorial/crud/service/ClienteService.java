package com.tutorial.crud.service;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();

    Cliente findById(Long id);

    Cliente save(Cliente cliente);

    /*ClienteDto save(ClienteDto clienteDto);*/

    /*ClienteDto getClienteById(Long id);
    List<ClienteDto> getAllClientes();
    ClienteDto createCliente(ClienteDto clienteDto);*/

    Cliente update(Long id, Cliente cliente);

    void delete(Long id);
}
