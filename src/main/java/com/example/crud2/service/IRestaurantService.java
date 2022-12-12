package com.example.crud2.service;

import com.example.crud2.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRestaurantService {

     RespRestaurantDto crearRestaurant (RestaurantDto restaurantDto);

     public List<RestaurantDto> listarRestaurants();

     RespRestaurantDto eliminarRestaurant(Long id);

     public ResponseEntity<RestaurantDto> actualizarRestaurant (RestaurantDto restaurantDto, Long id);

     public RestaurantDto obtenerPorId(Long id);

     public RespRestaurantDto agregaPlato (PlatoDto platoDto, Long id);

     public RespRestaurantDto eliminaPlato (Long id, Long id2);
     public Respuesta modificaPlato (PlatoDto platoDto, Long id);
     public List<PlatoDto> platoConMenosCalorias ();
     public RespRestListaPlatos listaDePlatosPorRestaurantOrdenada (Long id);

}
