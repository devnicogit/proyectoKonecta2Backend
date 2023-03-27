package com.tutorial.crud.repository;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.entity.Cliente;
import com.tutorial.crud.entity.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByTipoCliente(TipoCliente tipoCliente);
    /*List<ClienteDto> findByNombre(@Param("nombre") String nombre);*/

}
