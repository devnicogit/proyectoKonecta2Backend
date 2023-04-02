package com.tutorial.crud.swagger.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_cliente")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "tipoId", scope = TipoCliente.class)
//@JsonIdentityReference(alwaysAsId = false)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties("cliente")
public class TipoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("tipoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_id")
    private Long tipoId;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "tipoCliente")
    @JsonBackReference
    private Set<Cliente> cliente = new HashSet<>();



    public TipoCliente(Long tipoId, String nombre, Set<Cliente> cliente) {
        this.tipoId = tipoId;
        this.nombre = nombre;
        this.cliente = cliente;
    }

    public TipoCliente(String nombre, Set<Cliente> cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
    }

    public TipoCliente(String nombre) {
        this.nombre = nombre;
    }

    public TipoCliente(){

    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Cliente> getCliente() {
        return cliente;
    }

    public void setClientes(Set<Cliente> cliente) {
        this.cliente = cliente;
    }

}
