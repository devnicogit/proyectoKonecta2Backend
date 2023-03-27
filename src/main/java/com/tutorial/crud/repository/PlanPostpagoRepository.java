package com.tutorial.crud.repository;

import com.tutorial.crud.entity.PlanPostpago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanPostpagoRepository extends JpaRepository<PlanPostpago, Long> {
}
