package com.example.crud2.service;

import com.example.crud2.dto.PlatoDto;
import com.example.crud2.entity.Plato;
import com.example.crud2.repository.IPLatoRepository;
import org.springframework.stereotype.Service;

@Service
public class PlatoService implements IPlatoService{

    private IPLatoRepository PlatoRepository;

    private PlatoService (IPLatoRepository PlatoRepository){

        this.PlatoRepository = PlatoRepository;

    }

    public void crearPlato (PlatoDto platoDto, Long restaurant_id)
    {
       Plato plato = new Plato(platoDto.getNombre(),platoDto.getPrecio(),platoDto.getCalorias(),restaurant_id);
        PlatoRepository.save(plato);
    }
}
