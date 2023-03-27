package com.tutorial.crud.repository;

import com.tutorial.crud.entity.OrdenMigracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenMigracionRepository extends JpaRepository<OrdenMigracion, Long> {
}
