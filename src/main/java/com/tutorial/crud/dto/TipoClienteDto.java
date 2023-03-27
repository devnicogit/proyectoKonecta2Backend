package com.tutorial.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class TipoClienteDto {

    @NotBlank
    private Long tipoId;
    @NotBlank
    private String nombre;


    public TipoClienteDto() {
    }
    public TipoClienteDto(@NotBlank Long tipoId) {
        this.tipoId = tipoId;
    }

    public TipoClienteDto(@NotBlank Long tipoId, @NotBlank String nombre) {
        this.tipoId = tipoId;
        this.nombre = nombre;

    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
