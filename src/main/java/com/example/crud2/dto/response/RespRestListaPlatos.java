package com.example.crud2.dto.response;

import com.example.crud2.dto.request.PlatoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class RespRestListaPlatos {

    private List<PlatoDto> platoDto;
    private String mensaje;


}
