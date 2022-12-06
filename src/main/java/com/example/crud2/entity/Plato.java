package com.example.crud2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "plato")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long precio;
    private Long calorias;

    public Plato (String nombre, Long precio, Long calorias) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
        }

    public Plato(String nombre, Long precio, Long calorias, Restaurant restaurant) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
        this.restaurant = restaurant;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
