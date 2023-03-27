package com.tutorial.crud.repository;

import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class EjecutarArchivoRepositoryImpl implements EjecutarArchivoRepository {
    @Override
    public String ejecutarArchivo(String rutaEjecutable) throws IOException {
        Runtime.getRuntime().exec(rutaEjecutable);
        return "Archivo ejecutado correctamente.";
    }
}
