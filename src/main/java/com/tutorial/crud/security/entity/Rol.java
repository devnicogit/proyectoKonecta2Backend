package com.tutorial.crud.security.entity;

import com.fasterxml.jackson.annotation.*;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.swagger.entity.Cliente;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties("asesores")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    @ManyToMany(mappedBy = "roles")
    @JsonManagedReference
    private Set<Asesor> asesores = new HashSet<>();

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
