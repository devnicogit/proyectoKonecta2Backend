package com.tutorial.crud.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.tutorial.crud.security.entity.Asesor;
import com.tutorial.crud.swagger.entity.OrdenMigracion;
import com.tutorial.crud.swagger.entity.PlanPostpago;
import com.tutorial.crud.swagger.entity.Telefono;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrdenMigracionDto {


    //private String error;
    private Long id;
    private Long telefono;
    private Long asesor;
    private Long plan;

    //private Telefono telefono1;

    //private Asesor asesor1;


    //private PlanPostpago plan1;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    public OrdenMigracionDto(){

    }

    public OrdenMigracionDto(Long id, Long telefono, Long asesor, Long plan, LocalDate fecha) {
        this.id = id;
        this.telefono = telefono;
        this.asesor = asesor;
        this.plan = plan;
        this.fecha = fecha;
    }

    public OrdenMigracionDto(OrdenMigracion ordenMigracion) {
        this.id = ordenMigracion.getId();
        this.telefono = ordenMigracion.getTelefono().getId();
        this.asesor = ordenMigracion.getAsesor().getId();
        this.plan = ordenMigracion.getPlan().getPlanId();
        this.fecha = ordenMigracion.getFecha();
    }


  /*public OrdenMigracionDto(String error) {
        this.error = error;
    }*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public String getError() {
        return error;
    }*/

    /*public void setError(String error) {
        this.error = error;
    }*/

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Long getAsesor() {
        return asesor;
    }

    public void setAsesor(Long asesor) {
        this.asesor = asesor;
    }

    public Long getPlan() {
        return plan;
    }

    public void setPlan(Long plan) {
        this.plan = plan;
    }

   /* public Telefono getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(Telefono telefono1) {
        this.telefono1 = telefono1;
    }

    public Asesor getAsesor1() {
        return asesor1;
    }

    public void setAsesor1(Asesor asesor1) {
        this.asesor1 = asesor1;
    }

    public PlanPostpago getPlan1() {
        return plan1;
    }

    public void setPlan1(PlanPostpago plan1) {
        this.plan1 = plan1;
    }*/

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
