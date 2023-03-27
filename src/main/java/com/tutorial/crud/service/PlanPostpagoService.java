package com.tutorial.crud.service;

import com.tutorial.crud.dto.PlanPostpagoDto;
import com.tutorial.crud.entity.PlanPostpago;
import com.tutorial.crud.entity.TipoCliente;

import java.util.List;

public interface PlanPostpagoService {
    List<PlanPostpago> findAll();

    PlanPostpago findById(Long id);

    PlanPostpago save(PlanPostpago planPostpago);

    PlanPostpago update(Long id, PlanPostpago planPostpago);

    List<PlanPostpagoDto> getAllPlanesPostpago();
    PlanPostpagoDto getAllPlanesPostpagoById(Long id);

    void delete(Long id);
}