package com.example.crud2.controller;


import com.example.crud2.dto.PlatoDto;
import com.example.crud2.dto.RestaurantDto;
import com.example.crud2.service.IRestaurantService;
import com.example.crud2.service.RestaurantService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> crearRestaurant(@RequestBody RestaurantDto restaurantDto){
        return new ResponseEntity<>(restaurantService.crearRestaurant(restaurantDto), HttpStatus.OK);
        //return new ResponseEntity<>(cartService.guardarCart(cart),HttpStatus.OK);
    }
    @GetMapping("/listarRestaurant")
    public List<RestaurantDto> listarRestaurants()
    {
        return restaurantService.listarRestaurants();
    }

    @DeleteMapping("/borraRestaurant/{id}")
    public ResponseEntity<?> eliminarRestaurant(@PathVariable Long id)
    {
        return new ResponseEntity<>(restaurantService.eliminarRestaurant(id),HttpStatus.OK);
    }

    @GetMapping("/elegirRestaurantPorId/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(restaurantService.obtenerPorId(id),HttpStatus.OK);
    }

    @PutMapping("/actualizaRestaurant/{id}")
    public ResponseEntity<?> actualizarRestaurant(@RequestBody RestaurantDto restaurantDto, @PathVariable Long id)
    {

        return restaurantService.actualizarRestaurant(restaurantDto, id);

    }

    @PutMapping("/agregaPlato/{id}")
    public ResponseEntity<?> agregaPlato (@RequestBody PlatoDto platoDto, @PathVariable Long id)

    {
        return new ResponseEntity<>(restaurantService.agregaPlato(platoDto, id),HttpStatus.OK);
    }

    @PutMapping("/eliminarPlato/{id},{id2}")
    public ResponseEntity<?> eliminaPlato (@PathVariable Long id, @PathVariable Long id2)

    {
        return new ResponseEntity<>(restaurantService.eliminaPlato(id, id2),HttpStatus.OK);
    }

    @PutMapping("/modificaPlato/{id}")
    public ResponseEntity<?> modificaPlato (@RequestBody PlatoDto platoDto, @PathVariable Long id)

    {
        return new ResponseEntity<>(restaurantService.modificaPlato(platoDto, id),HttpStatus.OK);
    }

    @GetMapping("/platoConMenosCalorías/")
    public ResponseEntity<?> platoConMenosCalorias()
    {
        return new ResponseEntity<>(restaurantService.platoConMenosCalorias(),HttpStatus.OK);
    }

    @GetMapping("/listaDePlatosPorRestaurantOrdenada/{id}")
    public ResponseEntity<?> listaDePlatosPorRestaurantOrdenada(@PathVariable Long id)
    {
        return new ResponseEntity<>(restaurantService.listaDePlatosPorRestaurantOrdenada(id),HttpStatus.OK);
    }










}
