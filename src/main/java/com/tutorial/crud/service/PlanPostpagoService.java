package com.tutorial.crud.service;

import com.tutorial.crud.dto.PlanPostpagoDto;
import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.swagger.entity.Telefono;

import java.util.List;
import java.util.Optional;

public interface PlanPostpagoService {
    List<PlanPostpago> findAll();

    PlanPostpago findById(Long id);

    Optional<PlanPostpago> findByIds(Long id);

    PlanPostpago save(PlanPostpago planPostpago);

    PlanPostpago update(Long id, PlanPostpago planPostpago);

    List<PlanPostpagoDto> getAllPlanesPostpago();
    PlanPostpagoDto getAllPlanesPostpagoById(Long id);

    void delete(Long id);
}
