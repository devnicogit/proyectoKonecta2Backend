package com.tutorial.crud.swagger.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "telefono")
@JsonIdentityInfo(scope = Cliente.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="clienteId")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", unique = true, nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonIdentityInfo(scope = PlanPostpago.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="planId")
    @JsonIdentityReference(alwaysAsId = false)
    private PlanPostpago plan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIdentityInfo(scope = Cliente.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="clienteId")
    @JsonIdentityReference(alwaysAsId = false)
    private Cliente cliente;

    public Telefono(){

    }

    public Telefono(Long id, String numero, PlanPostpago plan, Cliente cliente) {
        this.id = id;
        this.numero = numero;
        this.plan = plan;
        this.cliente = cliente;
    }

    public Telefono(String numero, PlanPostpago plan, Cliente cliente) {
        this.numero = numero;
        this.plan = plan;
        this.cliente = cliente;
    }

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

    public PlanPostpago getPlan() {
        return plan;
    }

    public void setPlan(PlanPostpago plan) {
        this.plan = plan;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}