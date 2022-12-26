package com.example.crud2.dto.request;


import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class PlatoDto {
    private Long id;
    @NotBlank(message = "Se debe indicar el nombre")
    @Size(min = 1, max = 40, message = "El nombre debe tener como mín. 1 caracter y como max. 40")
    private String nombre;

    private Double precio;
    @Min (value=10,message = "El valor mínimo de calorías es 10")
    private int calorias;

    public PlatoDto(String nombre, Double precio, int calorias) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return "PlatoDto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", calorias=" + calorias +
                '}';
    }
}
