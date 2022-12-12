package com.example.crud2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RestaurantDto {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

    private Set<PlatoDto> platos;

    //public RestaurantDto(String nombre, String direccion, String telefono) {
    //    this.nombre = nombre;
     //   this.direccion = direccion;
     //   this.telefono = telefono;
    //}
}


