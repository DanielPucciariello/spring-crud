package com.example.crud2.service;

import com.example.crud2.dto.RestaurantDto;
import com.example.crud2.entity.Restaurant;
import com.example.crud2.repository.IRestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService implements IRestaurantService{

    private IRestaurantRepository restaurantRepository;

    private RestaurantService (IRestaurantRepository restaurantRepository ){

        this.restaurantRepository = restaurantRepository;

    }

    public List<RestaurantDto> listarRestaurants()
    {
        ObjectMapper mapper = new ObjectMapper();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<RestaurantDto> restaurantDtos = restaurants.stream().map( res -> {
            return new RestaurantDto(res.getId(),res.getNombre(), res.getDireccion(), res.getTelefono());
        }).collect(Collectors.toList());

        return restaurantDtos;

    }

    @Override
    public void eliminarRestaurant(Long id) {

        restaurantRepository.deleteById(id);
    }
    public void crearRestaurant (RestaurantDto restaurantDto)
    {
        Restaurant restaurant = new Restaurant(restaurantDto.getNombre(),restaurantDto.getDireccion(),restaurantDto.getTelefono());
        restaurantRepository.save(restaurant);
    }

   public ResponseEntity<RestaurantDto> actualizarRestaurant (RestaurantDto restaurantDto, Long id)

    {
        try {
            Restaurant restaurantActual = restaurantRepository.findById(id).get();

            restaurantActual.setNombre(restaurantDto.getNombre());
            restaurantActual.setDireccion(restaurantDto.getDireccion());
            restaurantActual.setTelefono(restaurantDto.getTelefono());

            restaurantRepository.save (restaurantActual);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
