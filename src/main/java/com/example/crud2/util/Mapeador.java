package com.example.crud2.util;

import com.example.crud2.dto.RestaurantDto;
import com.example.crud2.entity.Restaurant;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapeador {

    public static RestaurantDto entityToDto (Restaurant restaurant){

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(restaurant, RestaurantDto.class);
    }

    public static Restaurant dtoToEntity (RestaurantDto restaurantDto){

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(restaurantDto, Restaurant.class);
    }
}
