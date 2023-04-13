package com.tutorial.crud.repository;

import com.tutorial.crud.swagger.entity.OrdenMigracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenMigracionRepository extends JpaRepository<OrdenMigracion, Long> {

    @Query("SELECT om FROM OrdenMigracion om JOIN FETCH om.telefono")
    List<OrdenMigracion> findAllWithTelefono();
}
