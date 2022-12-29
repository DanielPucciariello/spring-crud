package com.example.crud2.dto.response;


import com.example.crud2.dto.request.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespRestaurantDto
{
    private RestaurantDto restaurantDto;
    private String mensajeDto;

}
