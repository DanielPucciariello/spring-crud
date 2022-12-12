package com.example.crud2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "platos")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long precio;
    private int calorias;

    @Override
    public String toString() {
        return "Plato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", calorias=" + calorias +
                ", restaurant=" + restaurant +
                '}';
    }

    public Plato (String nombre, Long precio, int calorias) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
        }

    public Plato(String nombre, Long precio, int calorias, Restaurant restaurant) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
        this.restaurant = restaurant;
    }

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
