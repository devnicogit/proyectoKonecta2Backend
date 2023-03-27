package com.tutorial.crud.service;

import com.tutorial.crud.repository.EjecutarArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EjecutarArchivoServiceImpl implements EjecutarArchivoService {

    @Autowired
    private EjecutarArchivoRepository ejecutarArchivoRepository;

    @Override
    public String ejecutarArchivo(String rutaEjecutable) throws IOException {
        return ejecutarArchivoRepository.ejecutarArchivo(rutaEjecutable);
    }
}
