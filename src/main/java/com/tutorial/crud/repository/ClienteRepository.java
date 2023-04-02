package com.tutorial.crud.repository;

import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByTipoCliente(TipoCliente tipoCliente);
    /*List<ClienteDto> findByNombre(@Param("nombre") String nombre);*/

}
