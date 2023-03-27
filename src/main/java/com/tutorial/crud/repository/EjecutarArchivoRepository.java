package com.tutorial.crud.repository;

import java.io.IOException;

public interface EjecutarArchivoRepository {
    String ejecutarArchivo(String rutaEjecutable) throws IOException;
}
