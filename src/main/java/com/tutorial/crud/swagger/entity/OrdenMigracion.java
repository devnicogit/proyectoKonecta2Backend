package com.tutorial.crud.swagger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutorial.crud.security.entity.Asesor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="orden_migracion")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
public class OrdenMigracion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telefono_id", nullable = false)
    private Telefono telefono;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asesor_id", nullable = false)
    private Asesor asesor;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", nullable = false)
    private PlanPostpago plan;

    @Column(name = "fecha")
    private LocalDate fecha;

    /*@OneToMany(mappedBy = "ordenMigracion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<DetalleOrdenMigracion> detallesOrdenMigracion;*/


  /*  public Set<DetalleOrdenMigracion> getDetallesOrdenMigracion() {
        return detallesOrdenMigracion;
    }*/


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
