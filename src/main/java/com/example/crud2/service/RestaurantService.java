package com.example.crud2.service;

import com.example.crud2.dto.*;
import com.example.crud2.entity.Plato;
import com.example.crud2.entity.Restaurant;
import com.example.crud2.repository.IPLatoRepository;
import com.example.crud2.repository.IRestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService implements IRestaurantService, IPlatoService
{

    private IRestaurantRepository restaurantRepository;

    private IPLatoRepository platoRepository;

    private RestaurantService (IRestaurantRepository restaurantRepository,IPLatoRepository platoRepository ){

        this.restaurantRepository = restaurantRepository;
        this.platoRepository = platoRepository;

    }



    public List<RestaurantDto> listarRestaurants()
    {
        ModelMapper mapper = new ModelMapper();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<RestaurantDto> restaurantDto = new ArrayList<>();

        restaurants.stream()
                .forEach(c->restaurantDto.add(mapper.map(c,RestaurantDto.class)));

        return restaurantDto;

    }

    @Override
    public RespRestaurantDto eliminarRestaurant(Long id) {

        Optional<Long> optionalid = Optional.ofNullable(id);
        restaurantRepository.deleteById(id);
        RespRestaurantDto resp = new RespRestaurantDto();
        resp.setMensaje("borrado exitoso");
        return resp;


    }
    public RespRestaurantDto crearRestaurant (RestaurantDto restaurantDto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Restaurant restaurant = modelMapper.map(restaurantDto,Restaurant.class);

        restaurant.getPlatos().forEach(i->i.setRestaurant(restaurant));
        Restaurant persistRestaurant = restaurantRepository.save(restaurant);

        RespRestaurantDto resp = new RespRestaurantDto();
        resp.setRestaurantDto(modelMapper.map(persistRestaurant,RestaurantDto.class));

        resp.setMensaje("Se guardó con éxito...");
        return resp;

    }

   public ResponseEntity<RestaurantDto> actualizarRestaurant (RestaurantDto restaurantDto, Long id)
    {
        ModelMapper modelMapper = new ModelMapper();
        Restaurant restaurant = modelMapper.map(restaurantDto,Restaurant.class);

        try {
            Restaurant restaurantActual = restaurantRepository.findById(id).get();

            restaurantActual.setNombre(restaurantDto.getNombre());
            restaurantActual.setDireccion(restaurantDto.getDireccion());
            restaurantActual.setTelefono(restaurantDto.getTelefono());

            restaurantActual.getPlatos().forEach(i->i.setRestaurant(restaurantActual));
            restaurantRepository.save (restaurantActual);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public RestaurantDto obtenerPorId(Long id) {
        ModelMapper mapper = new ModelMapper();

        Optional<Restaurant> restaurant =restaurantRepository.findById(id);

        RestaurantDto respuesta = mapper.map(restaurant,RestaurantDto.class);

        return respuesta;
    }

    public RespRestaurantDto agregaPlato (PlatoDto platoDto, Long id) {
        ModelMapper modelMapper = new ModelMapper();

        Plato plato = modelMapper.map(platoDto, Plato.class);

        //System.out.println(plato.toString());
        try {
            Restaurant restaurantActual = restaurantRepository.findById(id).get();

            plato.setRestaurant(restaurantActual);
            //System.out.println(plato.toString());
            platoRepository.save(plato);

            RespRestaurantDto resp = new RespRestaurantDto();
            resp.setRestaurantDto(modelMapper.map(restaurantActual, RestaurantDto.class));

            resp.setMensaje("El plato se guardó con éxito...");
            return resp;


        } catch (Exception exception) {
            RespRestaurantDto resp = new RespRestaurantDto();

            resp.setMensaje("El plato no pudo guardarse...");


            return resp;
        }
    }

        public RespRestaurantDto eliminaPlato (Long restaurantid, Long platoid) {
            ModelMapper modelMapper = new ModelMapper();
            try {
                Restaurant restaurantActual = restaurantRepository.findById(restaurantid).get();

                platoRepository.deleteById(platoid);

                RespRestaurantDto resp = new RespRestaurantDto();
                resp.setRestaurantDto(modelMapper.map(restaurantActual, RestaurantDto.class));

                resp.setMensaje("El plato se borró con éxito...");
                return resp;


            } catch (Exception exception) {
                RespRestaurantDto resp = new RespRestaurantDto();

                resp.setMensaje("Pasaron cosas...");


                return resp;
            }


        }
    public Respuesta modificaPlato (PlatoDto platoDto, Long id) {
        ModelMapper modelMapper = new ModelMapper();

        Plato plato = modelMapper.map(platoDto, Plato.class);

        try {
            Plato platoActual = platoRepository.findById(id).get();

            platoActual.setNombre(plato.getNombre());
            platoActual.setCalorias(plato.getCalorias());
            platoActual.setPrecio(plato.getPrecio());

            platoRepository.save(platoActual);
            Respuesta resp =new Respuesta();
            resp.setMensaje("El plato se modificó con éxito...");
            return resp;


        } catch (Exception exception) {
            Respuesta resp =new Respuesta();
            resp.setMensaje("Pasaron cosas...");

            return resp;
        }
    }

    public List<PlatoDto> platoConMenosCalorias () {
        ModelMapper mapper = new ModelMapper();
        List <Plato> platos = platoRepository.findAll();
        List <Plato> platosBajasCalorias = new ArrayList<>();
        List<PlatoDto> platoDto = new ArrayList<>();

        Plato platomenor = null;
        int caloriasMinimas = 30000;

        for (Plato plato : platos)
        {
            if (plato.getCalorias()<caloriasMinimas){
                platomenor=plato;
                caloriasMinimas=plato.getCalorias();
            }
        }

        for (Plato plato : platos)
        {
            System.out.println(plato.getCalorias());
            if (plato.getCalorias() == platomenor.getCalorias())
            {
                platosBajasCalorias.add(plato);

            }
        }


        platosBajasCalorias.stream()
                .forEach(c->platoDto.add(mapper.map(c,PlatoDto.class)));
        //resp.setPlatoDto(mapper.map(platomenor,PlatoDto.class));
        //resp.setMensaje("Todo bien?");

        return platoDto;

    }

    public RespRestListaPlatos listaDePlatosPorRestaurantOrdenada (Long id)
    {
        ModelMapper mapper = new ModelMapper();
        Restaurant restaurant =restaurantRepository.findById(id).get();

        List<Plato> platos = platoRepository.findAll();
        List<Plato> platosPorRestaurant= new ArrayList<>();

        for (Plato plato : platos)
        {
            if (plato.getRestaurant().getId()== restaurant.getId()){
                platosPorRestaurant.add(plato);

            }
        }
        Collections.sort(platosPorRestaurant, Comparator.comparing(Plato::getNombre));


        List <PlatoDto> platosDto= new ArrayList<>();
        platosPorRestaurant.stream()
                .forEach(c->platosDto.add(mapper.map(c,PlatoDto.class)));
        RestaurantDto restaurantDto = mapper.map(restaurant,RestaurantDto.class);
        RespRestListaPlatos resp = new RespRestListaPlatos();
        resp.setRestaurantDto(restaurantDto);
        resp.setPlatoDto(platosDto);
        resp.setMensaje("Listado generado");
        return resp;
    }



}
