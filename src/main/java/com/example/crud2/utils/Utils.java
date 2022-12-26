package com.example.crud2.utils;

import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.entity.Plato;
import com.example.crud2.entity.Restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static List<Restaurant> cargar(){

        Plato plato1 = new Plato("Flan", 500.00, 1000);
        Plato plato2 = new Plato("Asado", 1800.00, 1500);
        Plato plato3 = new Plato("Ensalada", 800.00, 800);

        Set<Plato> setplato1 = new HashSet<>();
        Set<Plato> setplato2 = new HashSet<>();
        Set<Plato> setplato3 = new HashSet<>();

        setplato1.add(plato1);
        setplato2.add(plato2);
        setplato3.add(plato3);

        List <Restaurant> restaurantlist = new ArrayList<>();

        Restaurant restaurant1 = new Restaurant(01L,"Pedro","Alberti 578","2222222",setplato1);
        Restaurant restaurant2 = new Restaurant(02L,"China Doll","Av. La Plata 711","3333333",setplato2);
        Restaurant restaurant3 = new Restaurant(03L,"Pucará","Alberdi 201","4444444444",setplato3);

        restaurantlist.add(restaurant1);
        restaurantlist.add(restaurant2);
        restaurantlist.add(restaurant3);

        plato1.setRestaurant(restaurant1);
        plato2.setRestaurant(restaurant2);
        plato3.setRestaurant(restaurant3);

        PlatoDto platoDto1 = new PlatoDto("Flan", 500.00, 1000);
        PlatoDto platoDto2 = new PlatoDto("Asado", 1800.00, 1500);
        PlatoDto platoDto3 = new PlatoDto("Ensalada", 800.00, 800);

        Set <PlatoDto> setplatoDto1 =new HashSet<>();
        Set <PlatoDto> setplatoDto2 =new HashSet<>();
        Set <PlatoDto> setplatoDto3 =new HashSet<>();

        setplatoDto1.add(platoDto1);
        setplatoDto2.add(platoDto2);
        setplatoDto3.add(platoDto3);

        List <RestaurantDto> listaEsperada = new ArrayList<>();
        listaEsperada.add(new RestaurantDto(01L,"Pedro","Alberti 578","2222222",setplatoDto1));
        listaEsperada.add(new RestaurantDto(02L,"China Doll","Av. La Plata 711","3333333",setplatoDto2));
        listaEsperada.add(new RestaurantDto(03L,"Pucará","Alberdi 201","4444444444",setplatoDto3));

        return restaurantlist;
    }
}
