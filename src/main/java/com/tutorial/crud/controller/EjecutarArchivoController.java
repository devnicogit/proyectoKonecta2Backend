package com.tutorial.crud.controller;

import com.tutorial.crud.service.EjecutarArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/archivo")
public class EjecutarArchivoController {

    @Autowired
    private EjecutarArchivoService ejecutarArchivoService;

    /*@GetMapping("/ejecutar")
    public String ejecutarArchivo(@RequestParam("C:\\Users\\nicol\\AppData\\Roaming\\Spotify\\Spotify.exe") String rutaEjecutable) {
        try {
            return ejecutarArchivoService.ejecutarArchivo(rutaEjecutable);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al ejecutar archivo.";
        }
    }*/

    @GetMapping("/ejecutar")
    public String ejecutarExe() throws IOException {
        //String[] command = {"\"C:\\Program Files (x86)\\CounterPath\\Bria\\bria.exe\"", "\"C:\\Users\\nicol\\AppData\\Roaming\\CounterPath Corporation\\Bria\\6.0\\default_user\\Logs\\Log.txt\""};
        String[] command = {"\"C:\\Program Files (x86)\\CounterPath\\Bria\\bria.exe\"", "argumentos"};
        Process proceso = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        StringBuilder salida = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            salida.append(linea + "\n");
        }

        int codigo = proceso.exitValue();

        return "Proceso finalizado con código " + codigo + "\n" + salida.toString();
    }
}
