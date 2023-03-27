package com.tutorial.crud.service;

import com.tutorial.crud.dto.TipoClienteDto;
import com.tutorial.crud.entity.Cliente;
import com.tutorial.crud.entity.TipoCliente;

import java.util.List;

public interface TipoClienteService {
    List<TipoCliente> findAll();

    TipoCliente findById(Long id);
    
    TipoCliente save(TipoCliente tipoCliente);

    /*TipoClienteDto save(TipoClienteDto tipoCliente);*/

    /*List<TipoClienteDto> getAllTipoClientes();
    TipoClienteDto getTipoClienteById(Long tipoClienteId);*/

    TipoCliente update(Long id, TipoCliente tipoCliente);

    void delete(Long id);
}
