package com.tutorial.crud.swagger.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
//@JsonIdentityInfo(scope = Cliente.class, generator= ObjectIdGenerators.PropertyGenerator.class, property="clienteId")
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties("tipoCliente")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;

    @ManyToMany
    @JoinTable(
            name = "cliente_tipo_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id"))
    @JsonManagedReference
    private Set<TipoCliente> tipoCliente = new HashSet<>();



    public Cliente(Long clienteId, String dni, String nombre, String apellido, String direccion) {
        this.clienteId = clienteId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public Cliente(String dni, String nombre, String apellido, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public Cliente(Long clienteId, String dni, String nombre, String apellido, String direccion, Set<TipoCliente> tipoCliente) {
        this.clienteId = clienteId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }

    public Cliente(String dni, String nombre, String apellido, String direccion, Set<TipoCliente> tipoCliente) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
    }

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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Set<TipoCliente> getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Set<TipoCliente> tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void addTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente.add(tipoCliente);
        tipoCliente.getCliente().add(this);
    }

    /*public String getTelefono() {
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
    }*/


}
