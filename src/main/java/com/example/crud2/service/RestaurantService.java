package com.example.crud2.service;

import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.dto.response.RespListaRest;
import com.example.crud2.dto.response.RespRestListaPlatos;
import com.example.crud2.dto.response.RespRestaurantDto;
import com.example.crud2.dto.response.Respuesta;
import com.example.crud2.entity.Plato;
import com.example.crud2.entity.Restaurant;
import com.example.crud2.exceptions.RestaurantNotFoundException;
import com.example.crud2.repository.IPLatoRepository;
import com.example.crud2.repository.IRestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService implements IRestaurantService, IPlatoService
{
    //Hago la inyección del repositorio de Restaurant y Plato
    private IRestaurantRepository restaurantRepository;

    private IPLatoRepository platoRepository;

    private RestaurantService (IRestaurantRepository restaurantRepository,IPLatoRepository platoRepository ){

        this.restaurantRepository = restaurantRepository;
        this.platoRepository = platoRepository;

    }


    // Este método hace un listado de los restaurants presentes en la base de datos
    public RespListaRest listarRestaurants()
    {
        ModelMapper mapper = new ModelMapper();
        RespListaRest resp = new RespListaRest();
        //Cargo todos los restaurants en la lista restaurants
        try {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            //Genero una nueva lista donde voy a guardar los restaurants en formato DTO
            List<RestaurantDto> restaurantDto = new ArrayList<>();
            //Mapeo los restaurants a restaurantsDTO

            restaurants.forEach(c -> restaurantDto.add(mapper.map(c, RestaurantDto.class)));
            //retorno el listado de restaurants como DTO
            resp.setRestaurantDto(restaurantDto);
            resp.setMensaje("Se generó el listado");
            return resp;
        }catch (Exception exception)
        {
            resp.setMensaje("El listado no pudo generarse");
            return resp;
        }
    }

    @Override
    public RespRestaurantDto eliminarRestaurant(Long id) {
        //Geneo un objeto de respuesta
        RespRestaurantDto resp = new RespRestaurantDto();
        //Intento borrar el restaurant si es que existe
        try {
            restaurantRepository.deleteById(id);
            resp.setMensaje("borrado exitoso");
        //Si no existe genero un mensaje que lo indica
        } catch (EmptyResultDataAccessException e){
            resp.setMensaje("el restaurant no existe");
        }
        //devuelvo el mensaje que corresponda
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

        // Este endpoint actualiza los datos del restaurant - No actualiza sus platos ya que hay un endpoint para ello
   {
       Optional<Restaurant> restaurantActual = restaurantRepository.findById(id);
       if (restaurantActual.isPresent()) {

       ModelMapper modelMapper = new ModelMapper();
       Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);

       restaurantActual.get().setNombre(restaurant.getNombre());
       restaurantActual.get().setDireccion(restaurant.getDireccion());
       restaurantActual.get().setTelefono(restaurant.getTelefono());

       restaurantActual.get().getPlatos().forEach(i -> i.setRestaurant(restaurantActual.get()));
       restaurantRepository.save(restaurantActual.get());

       return new ResponseEntity<>(HttpStatus.OK);
        }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

   }


    public RespRestaurantDto obtenerPorId(Long id) {

        //Cargo en un Optional el resultado de la búsqueda por id
        Optional<Restaurant> restaurant =restaurantRepository.findById(id);
        //Si existe
        if (restaurant.isPresent ()){
            ModelMapper mapper = new ModelMapper();
            //CReo un RestaurantDTO
            RespRestaurantDto resp = new RespRestaurantDto();
            //Convierto el restaurant hallado en Dto
            RestaurantDto restaurantDto = mapper.map(restaurant,RestaurantDto.class);
            //Completo la respuesta con el Dto y el mensaje y lo retorno
            resp.setRestaurantDto(restaurantDto);
            resp.setMensaje("Se encontró el restaurant!");
            return resp;
        }
        //Si no existe lanzo una excepción
        throw new RestaurantNotFoundException("El restaurant no se encontró");
    }

    public RespRestaurantDto agregaPlato (PlatoDto platoDto, Long id)
    {
        ModelMapper modelMapper = new ModelMapper();

        Plato plato = modelMapper.map(platoDto, Plato.class);


            Optional <Restaurant> restaurantActual = restaurantRepository.findById(id);
            if (restaurantActual.isPresent()){
            plato.setRestaurant(restaurantActual.get());

            platoRepository.save(plato);

            RespRestaurantDto resp = new RespRestaurantDto();
            resp.setRestaurantDto(modelMapper.map(restaurantActual.get(), RestaurantDto.class));

            resp.setMensaje("El plato se guardó con éxito...");
            return resp;
        }
            RespRestaurantDto resp = new RespRestaurantDto();
            resp.setMensaje("El plato no pudo guardarse...");
            return resp;
    }


        public RespRestaurantDto eliminaPlato (Long restaurantid, Long platoid) {
            ModelMapper modelMapper = new ModelMapper();
            Optional <Restaurant> restaurantActual = restaurantRepository.findById(restaurantid);
            if (restaurantActual.isPresent()){

            platoRepository.deleteById(platoid);

            RespRestaurantDto resp = new RespRestaurantDto();
            resp.setRestaurantDto(modelMapper.map(restaurantActual, RestaurantDto.class));

            resp.setMensaje("El plato se borró con éxito...");
            return resp;


            } else {
                RespRestaurantDto resp = new RespRestaurantDto();
                resp.setMensaje("El plato no pudo ser borrado");
                return resp;
            }


        }
    public Respuesta modificaPlato (PlatoDto platoDto, Long id) {
        ModelMapper modelMapper = new ModelMapper();

        Plato plato = modelMapper.map(platoDto, Plato.class);


            Optional <Plato> platoActual = platoRepository.findById(id);
            if (platoActual.isPresent()){

            platoActual.get().setNombre(plato.getNombre());
            platoActual.get().setCalorias(plato.getCalorias());
            platoActual.get().setPrecio(plato.getPrecio());

            platoRepository.save(platoActual.get());
            Respuesta resp =new Respuesta();
            resp.setMensaje("El plato se modificó con éxito...");
            return resp;


        } else {
            Respuesta resp =new Respuesta();
            resp.setMensaje("El plato no se pudo modificar");
            return resp;
        }
    }

    public List<PlatoDto> platoConMenosCalorias () {
        ModelMapper mapper = new ModelMapper();
        List <Plato> platos = platoRepository.findAll();
        List <Plato> platosBajasCalorias = new ArrayList<>();
        List<PlatoDto> platoDto = new ArrayList<>();
        int caloriasMinimas = platos.stream().findFirst().get().getCalorias();

        Plato platomenor = null;


        for (Plato plato : platos)
        {
            if (plato.getCalorias()<caloriasMinimas){
                platomenor=plato;
                caloriasMinimas=plato.getCalorias();
            }
        }

        for (Plato plato : platos)
        {
            if (plato.getCalorias() == platomenor.getCalorias())
            {
                platosBajasCalorias.add(plato);
            }
        }


        platosBajasCalorias.forEach(c->platoDto.add(mapper.map(c,PlatoDto.class)));


        return platoDto;

    }

    public RespRestListaPlatos listaDePlatosPorRestaurantOrdenada (Long id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            List<Plato> platos = platoRepository.findAll();
            List<Plato> platosPorRestaurant = new ArrayList<>();

            for (Plato plato : platos) {
                //if (plato.getRestaurant().getId()== restaurant.getId()){
                if (plato.getRestaurant().getId().equals(restaurant.get().getId())) {
                    platosPorRestaurant.add(plato);
                }
            }

            platosPorRestaurant.sort(Comparator.comparing(Plato::getNombre));

            List<PlatoDto> platosDto = new ArrayList<>();
            platosPorRestaurant.forEach(c -> platosDto.add(mapper.map(c, PlatoDto.class)));
            //RestaurantDto restaurantDto = mapper.map(restaurant, RestaurantDto.class);
            RespRestListaPlatos resp = new RespRestListaPlatos();
            resp.setPlatoDto(platosDto);
            resp.setMensaje("Listado generado");
            return resp;
        } else throw new RestaurantNotFoundException("El restaurant no se encontró");


        }


    }
