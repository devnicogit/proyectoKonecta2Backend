package com.tutorial.crud.service;

import com.tutorial.crud.repository.DetalleOrdenMigracionRepository;
import com.tutorial.crud.swagger.entity.DetalleOrdenMigracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOrdenMigracionImpl implements DetalleOrdenMigracionService{

    @Autowired
    private DetalleOrdenMigracionRepository detalleOrdenMigracionRepository;

    @Override
    public List<DetalleOrdenMigracion> findAll() {
        return detalleOrdenMigracionRepository.findAll();
    }

    @Override
    public DetalleOrdenMigracion findByCaracteristicasPlan(String caracteristicasPlan) {
        return detalleOrdenMigracionRepository.findByCaracteristicasPlan(caracteristicasPlan);
    }


    @Override
    public DetalleOrdenMigracion findById(Long id) {
        return detalleOrdenMigracionRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleOrdenMigracion save(DetalleOrdenMigracion detalleOrdenMigracion) {
        return detalleOrdenMigracionRepository.save(detalleOrdenMigracion);
    }
}
