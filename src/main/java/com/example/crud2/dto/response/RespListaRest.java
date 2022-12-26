package com.example.crud2.dto.response;

import com.example.crud2.dto.request.RestaurantDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RespListaRest {

    private String mensaje;
    private List<RestaurantDto> restaurantDto;


}
