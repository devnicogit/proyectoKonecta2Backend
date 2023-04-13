package com.tutorial.crud.service;

import com.tutorial.crud.swagger.entity.OrdenMigracion;

import javax.transaction.Transactional;
import java.util.List;

public interface OrdenMigracionService {

    List<OrdenMigracion> findAll();

    OrdenMigracion findById(Long id);

    OrdenMigracion save(OrdenMigracion ordenMigracion);

    @Transactional
    List<OrdenMigracion> findAllWithTelefono();

    void delete(Long id);
}
