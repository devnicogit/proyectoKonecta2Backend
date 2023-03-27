package com.tutorial.crud.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cliente")
@JsonIdentityInfo(scope = Cliente.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="clienteId")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    //@JsonIgnoreProperties("tipoId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_id", nullable = false)
    //@JsonIdentityInfo(scope = TipoCliente.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="tipoId")
    //@JsonIdentityReference(alwaysAsId = false)
    //@JsonProperty("tipoCliente")
    private TipoCliente tipoCliente;

    //@JsonIgnoreProperties("planId")

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", nullable = false)
    /*@JsonIdentityInfo(scope = PlanPostpago.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="planId")
    @JsonIdentityReference(alwaysAsId = false)*/
    //@JsonProperty("planPostpago")
    private PlanPostpago planPostpago;


    public Cliente(Long clienteId, String nombre, String apellido, String direccion, String telefono, TipoCliente tipoCliente, PlanPostpago planPostpago) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.planPostpago = planPostpago;
    }

    public Cliente(String nombre, String apellido, String direccion, String telefono, TipoCliente tipoCliente, PlanPostpago planPostpago) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.planPostpago = planPostpago;
    }

    /*public Cliente(Long clienteId, String nombre, String apellido, String direccion, String telefono) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }*/

    public Cliente(){

    }

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

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public PlanPostpago getPlanPostpago() {
        return planPostpago;
    }

    public void setPlanPostpago(PlanPostpago planPostpago) {
        this.planPostpago = planPostpago;
    }
}
