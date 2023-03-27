package com.tutorial.crud.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_cliente")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "tipoId", scope = TipoCliente.class)
@JsonIdentityReference(alwaysAsId = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("tipoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_id")
    private Long tipoId;

    @Column(name = "nombre")
    private String nombre;


    @OneToMany(mappedBy = "tipoCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnoreProperties("tipoCliente")
    //@JsonIgnoreProperties("clientes")
    @JsonIgnore
    private List<Cliente> clientes = new ArrayList<>();

    public TipoCliente(Long tipoId, String nombre, List<Cliente> clientes) {
        this.tipoId = tipoId;
        this.nombre = nombre;
        this.clientes = clientes;
    }

    public TipoCliente(String nombre){
        this.nombre = nombre;
    }

    /*@JsonCreator
    public TipoCliente(@JsonProperty("tipoId") Long tipoId, @JsonProperty("nombre") String nombre) {
        this.tipoId = tipoId;
        this.nombre = nombre;
    }*/


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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
