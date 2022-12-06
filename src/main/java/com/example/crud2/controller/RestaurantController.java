package com.example.crud2.controller;


import com.example.crud2.dto.RestaurantDto;
import com.example.crud2.service.IRestaurantService;
import com.example.crud2.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private IRestaurantService restaurantService;

    public RestaurantController (RestaurantService restaurantService){

        this.restaurantService = restaurantService;
    }

    @PostMapping("/crearRestaurant")
    public void crearRestaurant(@RequestBody RestaurantDto restaurantDto){
        restaurantService.crearRestaurant(restaurantDto);

    }
    @GetMapping("/listarRestaurant")
    public List<RestaurantDto> listarRestaurants()
    {
        return restaurantService.listarRestaurants();
    }

    @DeleteMapping("/restaurant/{id}")
    public void eliminarRestaurant(@PathVariable Long id)
    {
        restaurantService.eliminarRestaurant(id);
    }

    @PutMapping("/actualizaRestaurant/{id}")
    public ResponseEntity<?> actualizarRestaurant(@RequestBody RestaurantDto restaurantDto, @PathVariable Long id)
    {

        return restaurantService.actualizarRestaurant(restaurantDto, id);

    }







}
