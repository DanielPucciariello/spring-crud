package com.example.crud2.controller;


import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.service.IRestaurantService;
import com.example.crud2.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RestaurantController {

    private IRestaurantService restaurantService;

    public RestaurantController (RestaurantService restaurantService){

        this.restaurantService = restaurantService;
    }

    @PostMapping("/crearRestaurant")
    public ResponseEntity<?> crearRestaurant(@Valid @RequestBody RestaurantDto restaurantDto){
        return new ResponseEntity<>(restaurantService.crearRestaurant(restaurantDto), HttpStatus.OK);
    }
    @GetMapping("/listarRestaurant")
    public ResponseEntity<?> listarRestaurants()
    {
        return new ResponseEntity<>(restaurantService.listarRestaurants(),HttpStatus.OK);
    }

    @DeleteMapping("/borraRestaurant/{id}")
    public ResponseEntity<?> eliminarRestaurant(@PathVariable Long id)
    {
        return new ResponseEntity<>(restaurantService.eliminarRestaurant(id),HttpStatus.OK);
    }

    @GetMapping("/obtenerRestaurantPorId/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(restaurantService.obtenerPorId(id),HttpStatus.OK);
    }

    @PutMapping("/actualizaRestaurant/{id}")
    public ResponseEntity<?> actualizarRestaurant(@Valid @RequestBody RestaurantDto restaurantDto, @PathVariable Long id)
    {

        return restaurantService.actualizarRestaurant(restaurantDto, id);

    }

    @PutMapping("/agregaPlato/{id}")
    public ResponseEntity<?> agregaPlato (@Valid @RequestBody PlatoDto platoDto, @PathVariable Long id)

    {
        return new ResponseEntity<>(restaurantService.agregaPlato(platoDto, id),HttpStatus.OK);
    }

    @PutMapping("/eliminarPlato/{id},{id2}")
    public ResponseEntity<?> eliminaPlato (@PathVariable Long id, @PathVariable Long id2)

    {
        return new ResponseEntity<>(restaurantService.eliminaPlato(id, id2),HttpStatus.OK);
    }

    @PutMapping("/modificaPlato/{id}")
    public ResponseEntity<?> modificaPlato (@Valid @RequestBody PlatoDto platoDto, @PathVariable Long id)

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
