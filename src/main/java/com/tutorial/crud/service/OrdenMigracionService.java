package com.tutorial.crud.service;

import com.tutorial.crud.swagger.entity.OrdenMigracion;

import java.util.List;

public interface OrdenMigracionService {

    List<OrdenMigracion> findAll();

    OrdenMigracion findById(Long id);

    OrdenMigracion save(OrdenMigracion ordenMigracion);

    void delete(Long id);
}
