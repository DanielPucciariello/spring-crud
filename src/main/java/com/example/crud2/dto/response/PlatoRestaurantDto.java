package com.example.crud2.dto.response;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class PlatoRestaurantDto {

    private String nombrePlato;

    private Double precio;

    private int calorias;

    private String nombreRestaurant;

    private String direccion;

    private String telefono;

}
