package com.tutorial.crud.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tutorial.crud.swagger.entity.Cliente;
import com.tutorial.crud.swagger.entity.PlanPostpago;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class TelefonoDto {

    @NotNull
    private Long id;
    @NotBlank
    private String numero;

    private Long planPostpago;
    //@JsonProperty("cliente")
    private Long cliente;

    private Set<Long> tipoClienteIds;

    //private PlanPostpagoDto planPostpagoDto;

    //private ClienteDto clienteDto;

    @NotEmpty
    private Set<Float> planPostpago1 = new HashSet<>();

    private Set<PlanPostpagoDto> planPostpago2 = new HashSet<>();

    @NotEmpty
    private Set<Float> cliente1 = new HashSet<>();

    private Set<ClienteDto> cliente2 = new HashSet<>();

    public TelefonoDto(){
    }

    public TelefonoDto(Long id) {
        this.id = id;
    }

    public TelefonoDto(Long id, String numero, Long planPostpago, Long cliente) {
        this.id = id;
        this.numero = numero;
        this.planPostpago = planPostpago;
        this.cliente = cliente;
    }

    public TelefonoDto(String numero, Long planPostpago, Long cliente) {
        this.numero = numero;
        this.planPostpago = planPostpago;
        this.cliente = cliente;
    }


    public TelefonoDto(String numero, Long cliente, Long planPostpago, Set<Long> tipoClienteIds) {
        this.numero = numero;
        this.cliente = cliente;
        this.planPostpago = planPostpago;
        this.tipoClienteIds = tipoClienteIds;
    }

    /*public TelefonoDto(String numero,PlanPostpagoDto planPostpagoDto, ClienteDto clienteDto) {
        this.numero = numero;
        this.planPostpagoDto = planPostpagoDto;
        this.clienteDto = clienteDto;
    }*/

    public TelefonoDto(String numero, Set<Float> planPostpago1, Set<Float> cliente1) {
        this.numero = numero;
        this.planPostpago1 = planPostpago1;
        this.cliente1 = cliente1;
    }

    /*public TelefonoDto(String numero, Set<PlanPostpagoDto> planPostpago2, Set<ClienteDto> cliente2) {
        this.numero = numero;
        this.planPostpago2 = planPostpago2;
        this.cliente2 = cliente2;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getPlanPostpago() {
        return planPostpago;
    }

    public void setPlanPostpago(Long planPostpago) {
        this.planPostpago = planPostpago;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public void setTipoClienteIds(Set<Long> tipoClienteIds) {
        this.tipoClienteIds = tipoClienteIds;
    }

    public Set<Long> getTipoClienteIds() {
        return tipoClienteIds;
    }

    /* public PlanPostpagoDto getPlanPostpagoDto() {
        return planPostpagoDto;
    }

    public void setPlanPostpagoDto(PlanPostpagoDto planPostpagoDto) {
        this.planPostpagoDto = planPostpagoDto;
    }

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }*/

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

    public Set<Float> getCliente1() {
        return cliente1;
    }

    public void setCliente1(Set<Float> cliente1) {
        this.cliente1 = cliente1;
    }

    public Set<ClienteDto> getCliente2() {
        return cliente2;
    }

    public void setCliente2(Set<ClienteDto> cliente2) {
        this.cliente2 = cliente2;
    }

}
