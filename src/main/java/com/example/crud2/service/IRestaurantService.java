package com.example.crud2.service;

import com.example.crud2.dto.RestaurantDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRestaurantService {

     void crearRestaurant (RestaurantDto restaurantDto);

     public List<RestaurantDto> listarRestaurants();

     void eliminarRestaurant(Long id);

     public ResponseEntity<RestaurantDto> actualizarRestaurant (RestaurantDto restaurantDto, Long id);

}
