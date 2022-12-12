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


}
