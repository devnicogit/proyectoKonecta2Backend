package com.tutorial.crud.repository;

import com.tutorial.crud.swagger.entity.DetalleOrdenMigracion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleOrdenMigracionRepository extends JpaRepository<DetalleOrdenMigracion, Long> {

    DetalleOrdenMigracion findByCaracteristicasPlan(String caracteristicasPlan);
}
