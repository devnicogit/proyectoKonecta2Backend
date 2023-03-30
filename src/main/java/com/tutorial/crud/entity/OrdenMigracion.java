package com.tutorial.crud.entity;

import com.tutorial.crud.security.entity.Asesor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="orden_migracion")
public class OrdenMigracion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telefono_id", nullable = false)
    private Telefono telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesor_id", nullable = false)
    private Asesor asesor;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private PlanPostpago plan;

    @Column(name = "fecha")
    private LocalDate fecha;

    public OrdenMigracion() {
    }

    public OrdenMigracion(Long id, Telefono telefono, Asesor asesor, PlanPostpago plan, LocalDate fecha) {
        this.id = id;
        this.telefono = telefono;
        this.asesor = asesor;
        this.plan = plan;
        this.fecha = fecha;
    }

    public OrdenMigracion(Telefono telefono, Asesor asesor, PlanPostpago plan, LocalDate fecha) {
        this.telefono = telefono;
        this.asesor = asesor;
        this.plan = plan;
        this.fecha = fecha;
    }

    /*public OrdenMigracion(Long id, Asesor asesor, Cliente cliente, PlanPostpago plan, LocalDate fecha) {
        this.id = id;
        this.asesor = asesor;
        this.cliente = cliente;
        this.plan = plan;
        this.fecha = fecha;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }

    /*public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }*/

    public PlanPostpago getPlan() {
        return plan;
    }

    public void setPlan(PlanPostpago plan) {
        this.plan = plan;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }
}
