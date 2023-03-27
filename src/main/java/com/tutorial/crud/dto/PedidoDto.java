package com.tutorial.crud.dto;



import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.security.entity.Usuario;

import java.util.HashSet;
import java.util.Set;

public class PedidoDto {

    private Integer idped;

    private Integer usuario;

    private Integer cantidad;

    private Float precioUnitario;

    private Float precioTotal;
    private Set<Integer> producto = new HashSet<>();
    private Set<Producto> productos = new HashSet<>();
    private Boolean estado;

    private Usuario usuarios;


    public PedidoDto() {
    }

    public PedidoDto(Integer usuario, Integer cantidad, Float precioUnitario, Float precioTotal, Boolean estado) {
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    public PedidoDto(Usuario usuarios, Integer cantidad, Float precioUnitario, Float precioTotal, Boolean estado) {
        this.usuarios = usuarios;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }


    public Integer getIdped() {
        return idped;
    }

    public void setIdped(Integer idped) {
        this.idped = idped;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
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

    public Set<Integer> getProducto() {
        return producto;
    }

    public void setProducto(Set<Integer> producto) {
        this.producto = producto;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }
}
