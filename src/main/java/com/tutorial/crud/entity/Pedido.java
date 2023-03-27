package com.tutorial.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorial.crud.security.entity.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idped;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Usuario usuario;
    @NotNull
    private Integer cantidad;
    @NotNull
    private float precioUnitario;
    @NotNull
    private float precioTotal;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "pedido_producto", joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id"))
    @JsonIgnore
    private Set<Producto> producto = new HashSet<>();


    @NotNull
    private Boolean estado;

    /*public Pedido(Optional<Usuario> usuario, Integer cantidad, Float precioUnitario, Float precioTotal, Boolean estado) {
    }*/

    /*public Pedido(int idped, Usuario usuario, Integer cantidad, Float precioUnitario, Float precioTotal, Boolean estado) {
        this.idped = idped;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }*/

    public Pedido(){

    }

    public Pedido(Usuario usuario, Integer cantidad, Float precioUnitario, Float precioTotal, Boolean estado) {
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    /*public Pedido(Integer cantidad, Float precioUnitario, Float precioTotal, Boolean estado) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }*/


    public Integer getIdped() {
        return idped;
    }

    public void setIdped(Integer idped) {
        this.idped = idped;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Set<Producto> getProducto() {
        return producto;
    }

    public void setProducto(Set<Producto> producto) {
        this.producto = producto;
    }

    /*public void setProducto(Set<Producto> producto) {
        this.producto = producto;
        for(Producto productos: producto) {
            productos.set(this);
        }
    }*/

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
