package com.tutorial.crud.dto;

import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.dto.RolDto;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.swagger.entity.TipoCliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class ClienteDto {


    private Long clienteId;
    @NotBlank
    private String dni;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String direccion;


    private Set<TipoClienteDto> tipoCliente = new HashSet<>();
    private Set<TipoCliente> tipoCliente1 = new HashSet<>();

    private Set<Long> tipoClienteIds;


    public ClienteDto() {
    }
    public ClienteDto(Long clienteId) {
        this.clienteId = clienteId;
    }

    public ClienteDto(String dni, String nombre, String apellido, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<TipoClienteDto> getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Set<TipoClienteDto> tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Set<TipoCliente> getTipoCliente1() {
        return tipoCliente1;
    }

    public void setTipoCliente1(Set<TipoCliente> tipoCliente1) {
        this.tipoCliente1 = tipoCliente1;
    }

    public Set<Long> getTipoClienteIds() {
        return tipoClienteIds;
    }

    public void setTipoClienteIds(Set<Long> tipoClienteIds) {
        this.tipoClienteIds = tipoClienteIds;
    }
}
