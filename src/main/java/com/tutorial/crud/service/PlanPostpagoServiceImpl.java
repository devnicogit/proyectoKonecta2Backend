package com.tutorial.crud.service;

import com.tutorial.crud.dto.PlanPostpagoDto;
import com.tutorial.crud.entity.PlanPostpago;
import com.tutorial.crud.entity.TipoCliente;
import com.tutorial.crud.repository.PlanPostpagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanPostpagoServiceImpl implements PlanPostpagoService {

    @Autowired
    private PlanPostpagoRepository planPostpagoRepository;

    @Override
    public List<PlanPostpago> findAll() {
        return planPostpagoRepository.findAll();
    }

    @Override
    public PlanPostpago findById(Long id) {
        return planPostpagoRepository.findById(id).orElse(null);
    }

    @Override
    public PlanPostpago save(PlanPostpago planPostpago) {
        return planPostpagoRepository.save(planPostpago);
    }

    @Override
    public PlanPostpago update(Long id, PlanPostpago planPostpago) {
        Optional<PlanPostpago> planPostpagoEncontrado = planPostpagoRepository.findById(id);
        if (planPostpagoEncontrado.isPresent()) {
            PlanPostpago planPostpagoActualizado = planPostpagoEncontrado.get();
            planPostpagoActualizado.setNombrePlan(planPostpago.getNombrePlan());
            planPostpagoActualizado.setCostoMensual(planPostpago.getCostoMensual());


            return planPostpagoRepository.save(planPostpagoActualizado);
        } else {
            throw new IllegalArgumentException("Plan Postpago no encontrado");
        }
    }

    @Override
    public List<PlanPostpagoDto> getAllPlanesPostpago() {
        List<PlanPostpago> planPostpagoList = planPostpagoRepository.findAll();
        List<PlanPostpagoDto> planPostpagoDTOList = new ArrayList<>();
        for (PlanPostpago planPostpago : planPostpagoList) {
            PlanPostpagoDto planPostpagoDTO = new PlanPostpagoDto();
            planPostpagoDTO.setPlanId(planPostpago.getPlanId());
            planPostpagoDTO.setNombrePlan(planPostpago.getNombrePlan());
            planPostpagoDTO.setCostoMensual(planPostpago.getCostoMensual());
            planPostpagoDTOList.add(planPostpagoDTO);
        }
        return planPostpagoDTOList;
    }

    @Override
    public PlanPostpagoDto getAllPlanesPostpagoById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        planPostpagoRepository.deleteById(id);
    }
}
