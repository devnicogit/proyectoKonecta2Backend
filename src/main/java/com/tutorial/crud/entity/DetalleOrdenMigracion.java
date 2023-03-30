package com.tutorial.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "detalle_orden_migracion")
public class DetalleOrdenMigracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_migracion_id", nullable = false)
    private OrdenMigracion ordenMigracion;

    @Column(name = "caracteristicas_plan")
    private String caracteristicasPlan;

    @Column(name = "detalles_asesor")
    private String detallesAsesor;

    public DetalleOrdenMigracion(){}


    public DetalleOrdenMigracion(Long id, OrdenMigracion ordenMigracion, String caracteristicasPlan, String detallesAsesor) {
        this.id = id;
        this.ordenMigracion = ordenMigracion;
        this.caracteristicasPlan = caracteristicasPlan;
        this.detallesAsesor = detallesAsesor;
    }

    public DetalleOrdenMigracion(OrdenMigracion ordenMigracion, String caracteristicasPlan, String detallesAsesor) {
        this.ordenMigracion = ordenMigracion;
        this.caracteristicasPlan = caracteristicasPlan;
        this.detallesAsesor = detallesAsesor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdenMigracion getOrdenMigracion() {
        return ordenMigracion;
    }

    public void setOrdenMigracion(OrdenMigracion ordenMigracion) {
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
