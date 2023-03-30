package com.tutorial.crud.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plan_postpago")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "planId", scope = PlanPostpago.class)
@JsonIdentityReference(alwaysAsId = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlanPostpago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("planId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "nombre_plan")
    private String nombrePlan;

    @Column(name = "costo_mensual")
    private BigDecimal costoMensual;

    /*@OneToMany(mappedBy = "planPostpago", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnoreProperties("clientes")
    @JsonIgnore
    private List<Cliente> clientes = new ArrayList<>();*/

    public PlanPostpago() {

    }


    public PlanPostpago(String nombrePlan) {
        this.nombrePlan = nombrePlan;

    }

    public PlanPostpago(String nombrePlan, BigDecimal costoMensual) {
        this.nombrePlan = nombrePlan;
        this.costoMensual = costoMensual;
    }

    /*public PlanPostpago(Long planId, String nombrePlan, BigDecimal costoMensual, List<Cliente> clientes) {
        this.planId = planId;
        this.nombrePlan = nombrePlan;
        this.costoMensual = costoMensual;
        this.clientes = clientes;
    }*/

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public BigDecimal getCostoMensual() {
        return costoMensual;
    }

    public void setCostoMensual(BigDecimal costoMensual) {
        this.costoMensual = costoMensual;
    }

   /* public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }*/
}
