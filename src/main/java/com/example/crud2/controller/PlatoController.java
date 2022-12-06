package com.example.crud2.controller;


import com.example.crud2.dto.PlatoDto;
import com.example.crud2.service.IPlatoService;
import com.example.crud2.service.PlatoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlatoController {
    private IPlatoService platoService;

    public PlatoController (PlatoService platoService){

        this.platoService = platoService;
    }



    @PostMapping("/crearPlato/{restaurant_id}")
    public void crearPlato(@RequestBody PlatoDto platoDto, @PathVariable Long restaurant_id){

        //platoService.crearPLato(platoDto, restaurant_id);

    }
}
