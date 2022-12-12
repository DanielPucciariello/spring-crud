package com.example.crud2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "restaurant")
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

    @OneToMany (mappedBy = "restaurant", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Plato> platos;

    public Restaurant(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", platos=" + platos +
                '}';
    }

}
