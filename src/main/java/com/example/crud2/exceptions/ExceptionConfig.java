package com.example.crud2.exceptions;

import com.example.crud2.dto.response.ErrorDto;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice()
public class ExceptionConfig {
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<?> notFoundException(RestaurantNotFoundException e){
        ErrorDto errorDto = new ErrorDto(404, e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validacionErronea(MethodArgumentNotValidException ex){
        List<ErrorDto> errores = ex.getBindingResult().getFieldErrors().stream()
                                 .map(err -> new ErrorDto(400,err.getDefaultMessage()))
                                  .distinct()
                                   .collect(Collectors.toList());
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }


}
