package com.tutorial.crud.service;



import com.tutorial.crud.swagger.entity.Telefono;

import java.util.List;

public interface TelefonoService {

    List<Telefono> findAll();

    Telefono findById(Long id);

    Telefono save(Telefono telefono);

    /*ClienteDto save(ClienteDto clienteDto);*/

    /*ClienteDto getClienteById(Long id);
    List<ClienteDto> getAllClientes();
    ClienteDto createCliente(ClienteDto clienteDto);*/

    Telefono update(Long id, Telefono telefono);

    void delete(Long id);
}
