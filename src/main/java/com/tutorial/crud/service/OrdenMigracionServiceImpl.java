package com.tutorial.crud.service;

import com.tutorial.crud.swagger.entity.OrdenMigracion;
import com.tutorial.crud.repository.OrdenMigracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenMigracionServiceImpl implements OrdenMigracionService{

    @Autowired
    private OrdenMigracionRepository ordenMigracionRepository;

    @Override
    public List<OrdenMigracion> findAll() {
        return ordenMigracionRepository.findAll();
    }

    @Override
    public OrdenMigracion findById(Long id) {
        return ordenMigracionRepository.findById(id).orElse(null);
    }

    @Override
    public OrdenMigracion save(OrdenMigracion ordenMigracion) {
        return ordenMigracionRepository.save(ordenMigracion);
    }

    @Override
    public void delete(Long id) {
        ordenMigracionRepository.deleteById(id);
    }
}
