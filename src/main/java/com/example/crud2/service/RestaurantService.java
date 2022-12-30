package com.example.crud2.service;
import com.example.crud2.dto.request.PlatoDto;
import com.example.crud2.dto.request.RestaurantDto;
import com.example.crud2.dto.response.*;
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
public class

RestaurantService implements IRestaurantService, IPlatoService
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
            resp.setMensajeDto("borrado exitoso");
        //Si no existe genero un mensaje que lo indica
        } catch (EmptyResultDataAccessException e){
            resp.setMensajeDto("el restaurant no existe");
        }
        //devuelvo el mensaje que corresponda
        return resp;


    }
    public RespRestaurantDto crearRestaurant (RestaurantDto restaurantDto)
    {
        ModelMapper modelMapper = new ModelMapper();
        //Mapeo el restaurantDto a restaurant
        Restaurant restaurant = modelMapper.map(restaurantDto,Restaurant.class);
        //Le asigno el restaurant a los platos recibidos
        restaurant.getPlatos().forEach(i->i.setRestaurant(restaurant));
        Restaurant persistRestaurant = restaurantRepository.save(restaurant);

        RespRestaurantDto resp = new RespRestaurantDto();
        //Devuelve el restaurant creado (se podría obviar esto alternativamente)
        resp.setRestaurantDto(modelMapper.map(persistRestaurant,RestaurantDto.class));
        //Devuelve el mensaje de guardado con exito
        resp.setMensajeDto("Se guardó con éxito...");
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
            resp.setMensajeDto("Se encontró el restaurant!");
            return resp;
        }
        //Si no existe lanzo una excepción
        throw new RestaurantNotFoundException("El restaurant no se encontró");
    }

    public RespRestaurantDto agregaPlato (PlatoDto platoDto, Long id)
    {
        ModelMapper modelMapper = new ModelMapper();
        //Convierto el platoDto a plato
        Plato plato = modelMapper.map(platoDto, Plato.class);

            //Siel restaurantexiste se agrega el plato
            Optional <Restaurant> restaurantActual = restaurantRepository.findById(id);
            if (restaurantActual.isPresent()){
            plato.setRestaurant(restaurantActual.get());

            platoRepository.save(plato);

            RespRestaurantDto resp = new RespRestaurantDto();
            resp.setRestaurantDto(modelMapper.map(restaurantActual.get(), RestaurantDto.class));

            resp.setMensajeDto("El plato se guardó con éxito...");
            return resp;
        }
            //En caso contrario se avisa del problema
            RespRestaurantDto respDto = new RespRestaurantDto();
            respDto.setMensajeDto("El plato no pudo guardarse...");
            return respDto;
    }

        public RespuestaDto eliminaPlato (Long id) {

            RespuestaDto respuestaDto = new RespuestaDto();

            try {
                platoRepository.deleteById(id);
                respuestaDto.setMensaje("borrado exitoso");
                //Si no existe genero un mensaje que lo indica
            } catch (EmptyResultDataAccessException e){
                respuestaDto.setMensaje("el restaurant no existe");
            }
            //devuelvo el mensaje que corresponda
            return respuestaDto;

        }
    public RespuestaDto modificaPlato (PlatoDto platoDto, Long id) {
        ModelMapper modelMapper = new ModelMapper();
        //Convierto el platoDto a plato
        Plato plato = modelMapper.map(platoDto, Plato.class);

            //Si el plato que se quiere modificar existe....
            Optional <Plato> platoActual = platoRepository.findById(id);
            if (platoActual.isPresent()){
            //...se cargan los nuevos datos
            platoActual.get().setNombre(plato.getNombre());
            platoActual.get().setCalorias(plato.getCalorias());
            platoActual.get().setPrecio(plato.getPrecio());
            //Se guardan los cambios
            platoRepository.save(platoActual.get());
            //Se avisa que se modificó con exito
            RespuestaDto respuestaDto =new RespuestaDto();
            respuestaDto.setMensaje("El plato se modificó con éxito...");
            return respuestaDto;


        } else {
            //En caso contrario se avisa que no se pudo efectuar la modificación
            RespuestaDto resp =new RespuestaDto();
            resp.setMensaje("El plato no se pudo modificar");
            return resp;
        }
    }
    public List<PlatoRestaurantDto> platoConMenosCalorias () {
        //Se traen todos losplatos del repositorio
        List <Plato> platos = platoRepository.findAll();
        //Se crea un listado donde van a estar lo platos con menos calorías
        List <Plato> platosBajasCalorias = new ArrayList<>();
        //Se crea un listado donde se van a retornar los datos de los platos y el restaurant al que pertenecen
        List<PlatoRestaurantDto> listaPlatoRestaurantDto = new ArrayList<>();
        //Tomo las calorías del primer plato como referencia para comenzar a iterar
        int caloriasMinimas = platos.stream().findFirst().get().getCalorias();

        Plato platomenor = null;
        // En este bucle obtengo cual es el número de calorías mas bajo
        for (Plato plato : platos)
        {
            if (plato.getCalorias()<caloriasMinimas){
                platomenor=plato;
                caloriasMinimas=plato.getCalorias();
            }
        }
        //Con el dato obtenido arriba genero la lista de platos que tengan ese valor
        for (Plato plato : platos)
        {
            if (plato.getCalorias() == platomenor.getCalorias())
            {
                platosBajasCalorias.add(plato);
            }
        }
        //Ya trabajando con el listado que tiene los platos con menos calorías genero la entidad de respuesta con la
        //información que quiero mostrar
        for (Plato plato : platosBajasCalorias)
        {
            listaPlatoRestaurantDto.add(new PlatoRestaurantDto(plato.getNombre(),plato.getPrecio(),
                    plato.getCalorias(),plato.getRestaurant().getNombre(),
                     plato.getRestaurant().getDireccion(),plato.getRestaurant().getTelefono()));
        }
        //No puedo dejar de comentar que entiendo que seguramente con streams() se podría hacer de una forma mas
        //elegante y compacta. Lametablemente no encontré la estructura luego de dedicarle bastante tiempo al tema
        //Me queda para investigar el tema posteriormente

        return listaPlatoRestaurantDto;
    }

    public RespRestListaPlatos listaDePlatosPorRestaurantOrdenada (Long id) {
        ModelMapper mapper = new ModelMapper();

        //Busoo el restaurant con el id recibido
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        //Si existe busco los platos del repositorio
        if (restaurant.isPresent()) {
            List<Plato> platos = platoRepository.findAll();
            List<Plato> platosPorRestaurant = new ArrayList<>();
            //Genero una lista con los platos que pertenecen a ese restaurant
            for (Plato plato : platos) {
                if (plato.getRestaurant().getId().equals(restaurant.get().getId())) {
                    platosPorRestaurant.add(plato);
                }
            }
            //Ordeno los platos del listado obtenido alfabeticamente por nombre
            platosPorRestaurant.sort(Comparator.comparing(Plato::getNombre));

            List<PlatoDto> platosDto = new ArrayList<>();
            //Genero un listado con la respuesta en formato Dto
            platosPorRestaurant.forEach(c -> platosDto.add(mapper.map(c, PlatoDto.class)));
            //Preparo la entidad de respuesta
            RespRestListaPlatos resp = new RespRestListaPlatos();
            resp.setPlatoDto(platosDto);
            resp.setMensaje("Listado generado");
            return resp;
            // Si no se encontró el restaurant se indica
            } else throw new RestaurantNotFoundException("El restaurant no se encontró");
        }


    }
