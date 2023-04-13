package com.tutorial.crud.service;


import com.tutorial.crud.swagger.entity.DetalleOrdenMigracion;

import java.util.List;

public interface DetalleOrdenMigracionService {

    List<DetalleOrdenMigracion> findAll();

    DetalleOrdenMigracion findById(Long id);

    DetalleOrdenMigracion save(DetalleOrdenMigracion detalleOrdenMigracion);
}
