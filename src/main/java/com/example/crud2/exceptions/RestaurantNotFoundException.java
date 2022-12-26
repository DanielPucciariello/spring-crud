package com.example.crud2.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(String message)
    {
        super(message);
    }
}


