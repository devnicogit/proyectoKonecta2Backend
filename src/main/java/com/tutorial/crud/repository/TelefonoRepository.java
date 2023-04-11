package com.tutorial.crud.repository;

import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {

    List<Telefono> findByCliente(Cliente cliente);

}
