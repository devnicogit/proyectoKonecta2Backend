package com.tutorial.crud.dto;

import com.tutorial.crud.swagger.entity.DetalleOrdenMigracion;
import com.tutorial.crud.swagger.entity.OrdenMigracion;



public class DetalleOrdenMigracionDto {

    private Long id;
    private Long ordenMigracion;
    private String caracteristicasPlan;
    private String detallesAsesor;

    public DetalleOrdenMigracionDto(){}

    public DetalleOrdenMigracionDto(Long id, Long ordenMigracion, String caracteristicasPlan, String detallesAsesor) {
        this.id = id;
        this.ordenMigracion = ordenMigracion;
        this.caracteristicasPlan = caracteristicasPlan;
        this.detallesAsesor = detallesAsesor;
    }

    public DetalleOrdenMigracionDto(Long ordenMigracion, String caracteristicasPlan, String detallesAsesor) {
        this.ordenMigracion = ordenMigracion;
        this.caracteristicasPlan = caracteristicasPlan;
        this.detallesAsesor = detallesAsesor;
    }

    public DetalleOrdenMigracionDto(DetalleOrdenMigracion detalleOrdenMigracion) {
        this.id = detalleOrdenMigracion.getId();
        this.ordenMigracion = detalleOrdenMigracion.getOrdenMigracion().getId();
        this.caracteristicasPlan = detalleOrdenMigracion.getCaracteristicasPlan();
        this.detallesAsesor = detalleOrdenMigracion.getDetallesAsesor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdenMigracion() {
        return ordenMigracion;
    }

    public void setOrdenMigracion(Long ordenMigracion) {
        this.ordenMigracion = ordenMigracion;
    }

    public String getCaracteristicasPlan() {
        return caracteristicasPlan;
    }

    public void setCaracteristicasPlan(String caracteristicasPlan) {
        this.caracteristicasPlan = caracteristicasPlan;
    }

    public String getDetallesAsesor() {
        return detallesAsesor;
    }

    public void setDetallesAsesor(String detallesAsesor) {
        this.detallesAsesor = detallesAsesor;
    }
}
