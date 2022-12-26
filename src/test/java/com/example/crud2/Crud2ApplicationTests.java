package com.example.crud2;

import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.dto.response.RespRestaurantDto;
import com.example.crud2.service.IRestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Crud2ApplicationTests {

    @Autowired
    IRestaurantService restaurantService;



    @Test
    void crearRestaurantTestOK (){

        // Arrange
        PlatoDto platoDto = new PlatoDto("Flan", 500.00, 6000);
        Set <PlatoDto> setplato= new HashSet<>();

        setplato.add(platoDto);
        RestaurantDto restaurantDto = new RestaurantDto("Pedro","Alberti 578","1234",setplato);
        RespRestaurantDto respRestaurantDtoEsperado = new RespRestaurantDto();
        respRestaurantDtoEsperado.setMensaje("Se guardó con éxito...");
        //Act
        RespRestaurantDto respRestaurantDtoReal = restaurantService.crearRestaurant(restaurantDto);

        // Assert

        assertEquals(respRestaurantDtoEsperado.getMensaje(),respRestaurantDtoReal.getMensaje());
    }


}
