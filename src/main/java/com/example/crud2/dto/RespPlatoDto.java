package com.example.crud2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RespPlatoDto {

    private List<PlatoDto> platoDto;
    private String mensaje;

}
