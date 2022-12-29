package com.example.crud2;

import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.entity.Plato;
import com.example.crud2.entity.Restaurant;
import com.example.crud2.repository.IRestaurantRepository;
import com.example.crud2.service.RestaurantService;
import org.assertj.core.internal.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceTestConMock {

    @Mock
    private IRestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void listarRestaurantsTestOk (){

        //arrange

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

        //act

        when (restaurantRepository.findAll()).thenReturn(restaurantlist);
        List<RestaurantDto> listaObtenida = restaurantService.listarRestaurants().getRestaurantDto();

        //assert

        Assertions.assertEquals(listaEsperada.size(),listaObtenida.size());
        Assertions.assertEquals(listaEsperada,listaObtenida);
        }

}
