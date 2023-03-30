package com.tutorial.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class TipoClienteDto {

    @NotBlank
    private Long tipoId;
    @NotBlank
    private String nombre;

    @NotBlank
    private Long cliente;

    private ClienteDto clienteDto;

    @NotBlank
    private Set<Float> cliente1 = new HashSet<>();

    private Set<ClienteDto> cliente2 = new HashSet<>();


    public TipoClienteDto() {
    }
    public TipoClienteDto(@NotBlank Long tipoId) {
        this.tipoId = tipoId;
    }

    public TipoClienteDto(@NotBlank Long tipoId, @NotBlank String nombre) {
        this.tipoId = tipoId;
        this.nombre = nombre;

    }

    public TipoClienteDto(Long tipoId, String nombre, Long cliente) {
        this.tipoId = tipoId;
        this.nombre = nombre;
        this.cliente = cliente;
    }

    public TipoClienteDto(String nombre, Long cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
    }

    public TipoClienteDto(Long tipoId, String nombre, ClienteDto clienteDto) {
        this.tipoId = tipoId;
        this.nombre = nombre;
        this.clienteDto = clienteDto;
    }

    public TipoClienteDto(Long tipoId, String nombre, Set<Float> cliente1) {
        this.tipoId = tipoId;
        this.nombre = nombre;
        this.cliente1 = cliente1;
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

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }
}
