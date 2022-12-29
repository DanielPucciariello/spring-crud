package com.example.crud2.service;

import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.dto.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRestaurantService {

     RespRestaurantDto crearRestaurant (RestaurantDto restaurantDto);

     RespListaRest listarRestaurants();

     RespRestaurantDto eliminarRestaurant(Long id);

     ResponseEntity<RestaurantDto> actualizarRestaurant (RestaurantDto restaurantDto, Long id);

     RespRestaurantDto obtenerPorId(Long id);

     RespRestaurantDto agregaPlato (PlatoDto platoDto, Long id);


     RespuestaDto eliminaPlato (Long id);
     RespuestaDto modificaPlato (PlatoDto platoDto, Long id);

     List<PlatoRestaurantDto> platoConMenosCalorias ();
     RespRestListaPlatos listaDePlatosPorRestaurantOrdenada (Long id);

}
