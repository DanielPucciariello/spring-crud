package com.example.crud2.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class RestaurantDto {

    private Long id;

    @NotBlank (message = "Se debe indicar el nombre")
    @Size (min = 1, max = 40, message = "El nombre debe tener como mín. 1 caracter y como max. 40")
    private String nombre;

    @NotBlank (message = "Se debe indicar la dirección")
    @Size (min = 5, max = 50, message = "La dirección debe tener como mín. 5 caracteres y como max. 50")
    private String direccion;

    private String telefono;

    private Set<PlatoDto> platos;

    public RestaurantDto(String nombre, String direccion, String telefono, Set<PlatoDto> platos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.platos = platos;
    }

    public RestaurantDto(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "RestaurantDto{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", platos=" + platos +
                '}';
    }
}


