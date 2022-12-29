package com.example.crud2;

import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.dto.response.PlatoRestaurantDto;
import com.example.crud2.dto.response.RespRestaurantDto;
import com.example.crud2.dto.response.RespuestaDto;
import com.example.crud2.service.IRestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Crud2ApplicationTests {

    @Autowired
    IRestaurantService restaurantService;

    @Test
    void crearRestaurantTestOK (){

        // Arrange
            //Creo un platoDto
        PlatoDto platoDto = new PlatoDto("Flan", 500.00, 6000);
            //Creo un set de platos para agregarlo al restaurant
        Set <PlatoDto> setplato= new HashSet<>();
            //Agrego el plato al restaurant
        setplato.add(platoDto);
            //Genero el restaurantDto que se va a crear
        RestaurantDto restaurantDto = new RestaurantDto("Pedro","Alberti 578","1234",setplato);
        RespRestaurantDto respRestaurantDtoEsperado = new RespRestaurantDto();
        respRestaurantDtoEsperado.setMensajeDto("Se guardó con éxito...");
        //Act
        RespRestaurantDto respRestaurantDtoReal = restaurantService.crearRestaurant(restaurantDto);

        // Assert

        assertEquals(respRestaurantDtoEsperado.getMensajeDto(),respRestaurantDtoReal.getMensajeDto());
    }

        @Test
        void modificaPlatoTestOK()
        {
         //Arrange
            PlatoDto platoDto = new PlatoDto("Mondongo", 2345.00, 8000);
            Long id =10L;
            RespuestaDto respuestaEsperada = new RespuestaDto();
            respuestaEsperada.setMensaje("El plato se modificó con éxito...");

         //Act
            RespuestaDto respuestaDtoReal = restaurantService.modificaPlato(platoDto,id);

         // Assert

            assertEquals(respuestaDtoReal.getMensaje(),respuestaEsperada.getMensaje());

        }
    @Test
    void modificaPlatoTestNoOK()
    {
        //Arrange
        PlatoDto platoDto = new PlatoDto("Mondongo", 2345.00, 8000);
        Long id =50L;
        RespuestaDto respuestaEsperada = new RespuestaDto();
        respuestaEsperada.setMensaje("El plato no se pudo modificar");

        //Act
        RespuestaDto respuestaDtoReal = restaurantService.modificaPlato(platoDto,id);

        // Assert

        assertEquals(respuestaDtoReal.getMensaje(),respuestaEsperada.getMensaje());

    }
    @Test
    void platoConMenosCaloriasTestOK()
        {
            //Arrange
            PlatoRestaurantDto platoRestaurantDto = new PlatoRestaurantDto("Bife de chorizo",2500.00,142,"Los Chanchitos","Av. Angel Gallardo 601","011 4854-4030");
             List<PlatoRestaurantDto> listaPlatoRestaurantDtoEsperado = new ArrayList<>();
             listaPlatoRestaurantDtoEsperado.add(platoRestaurantDto);

            //Act

            List<PlatoRestaurantDto> listaPlatoRestaurantDtoObtenido = restaurantService.platoConMenosCalorias();

            //Assert

            assertEquals(listaPlatoRestaurantDtoEsperado,listaPlatoRestaurantDtoObtenido);
        }
    @Test
    void eliminaPlatoTestOK()
    {
            //Arrange
                Long id = 11L;
                RespuestaDto respuestaDtoEsperada =new RespuestaDto();
                respuestaDtoEsperada.setMensaje("borrado exitoso");

            //Act

                RespuestaDto respuestaDtoObtenida = restaurantService.eliminaPlato(id);

            //Assert

                assertEquals(respuestaDtoEsperada.getMensaje(),respuestaDtoObtenida.getMensaje());

    }
}
