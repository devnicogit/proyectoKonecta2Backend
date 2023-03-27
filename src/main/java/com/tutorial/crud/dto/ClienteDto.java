package com.tutorial.crud.dto;

import com.tutorial.crud.entity.Cliente;
import com.tutorial.crud.entity.PlanPostpago;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ClienteDto {

    @NotBlank
    private Long clienteId;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String direccion;
    @NotBlank
    private String telefono;

    private Long tipoCliente;
    private Long planPostpago;

    private TipoClienteDto tipoClienteDto;
    private PlanPostpagoDto planPostpagoDto;
    @NotBlank
    private Set<Float> tipoCliente1 = new HashSet<>();

    private Set<TipoClienteDto> tipoCliente2 = new HashSet<>();
    @NotBlank
    private Set<Float> planPostpago1 = new HashSet<>();
    private Set<PlanPostpagoDto> planPostpago2= new HashSet<>();



    public ClienteDto() {
    }
    public ClienteDto(Long clienteId) {
        this.clienteId = clienteId;
    }

    public ClienteDto(Long clienteId, String nombre, String apellido, String direccion, String telefono, Long tipoCliente, Long planPostpago) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.planPostpago = planPostpago;
    }

    public ClienteDto(String nombre, String apellido, String direccion, String telefono, Long tipoCliente, Long planPostpago) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.planPostpago = planPostpago;
    }


    public ClienteDto(Long clienteId, String nombre, String apellido, String direccion, String telefono, TipoClienteDto tipoClienteDto, PlanPostpagoDto planPostpagoDto) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoClienteDto = tipoClienteDto;
        this.planPostpagoDto = planPostpagoDto;
    }

    public ClienteDto(String nombre, String apellido, String direccion, String telefono, TipoClienteDto tipoClienteDto, PlanPostpagoDto planPostpagoDto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoClienteDto = tipoClienteDto;
        this.planPostpagoDto = planPostpagoDto;
    }

    public ClienteDto(Cliente cliente) {
        this.clienteId = cliente.getClienteId();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.telefono = cliente.getTelefono();
        this.direccion = cliente.getDireccion();
        if (cliente.getTipoCliente() != null) {
            this.tipoCliente = cliente.getTipoCliente().getTipoId();
        }
        if (cliente.getPlanPostpago() != null) {
            this.planPostpago = cliente.getPlanPostpago().getPlanId();
        }
    }



    /*public ClienteDto(String nombre, String apellido, String direccion, String telefono, Set<Float> tipoCliente1, Set<Float> planPostpago1) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente1 = tipoCliente1;
        this.planPostpago1 = planPostpago1;
    }*/

    /*public ClienteDto(String nombre, String apellido, String direccion, String telefono, Set<TipoClienteDto> tipoCliente2, Set<PlanPostpagoDto> planPostpago2) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente2 = tipoCliente2;
        this.planPostpago2 = planPostpago2;
    }*/

    /*public ClienteDto(String nombre, String apellido, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }*/

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Long tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Long getPlanPostpago() {
        return planPostpago;
    }

    public void setPlanPostpago(Long planPostpago) {
        this.planPostpago = planPostpago;
    }

    public Set<Float> getTipoCliente1() {
        return tipoCliente1;
    }

    public void setTipoCliente1(Set<Float> tipoCliente1) {
        this.tipoCliente1 = tipoCliente1;
    }

    public Set<TipoClienteDto> getTipoCliente2() {
        return tipoCliente2;
    }

    public void setTipoCliente2(Set<TipoClienteDto> tipoCliente2) {
        this.tipoCliente2 = tipoCliente2;
    }

    public Set<Float> getPlanPostpago1() {
        return planPostpago1;
    }

    public void setPlanPostpago1(Set<Float> planPostpago1) {
        this.planPostpago1 = planPostpago1;
    }

    public Set<PlanPostpagoDto> getPlanPostpago2() {
        return planPostpago2;
    }

    public void setPlanPostpago2(Set<PlanPostpagoDto> planPostpago2) {
        this.planPostpago2 = planPostpago2;
    }


    public TipoClienteDto getTipoClienteDto() {
        return tipoClienteDto;
    }

    public void setTipoClienteDto(TipoClienteDto tipoClienteDto) {
        this.tipoClienteDto = tipoClienteDto;
    }

    public PlanPostpagoDto getPlanPostpagoDto() {
        return planPostpagoDto;
    }

    public void setPlanPostpagoDto(PlanPostpagoDto planPostpagoDto) {
        this.planPostpagoDto = planPostpagoDto;
    }
}
