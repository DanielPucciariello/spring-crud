package com.example.crud2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlatoDto {
    private Long id;
    private String nombre;
    private Long precio;
    private Long calorias;

    public PlatoDto(String nombre, Long precio, Long calorias) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
    }
}
