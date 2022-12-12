package com.example.crud2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class RespRestListaPlatos {

    private RestaurantDto restaurantDto;
    private List<PlatoDto> platoDto;
    private String mensaje;


}
