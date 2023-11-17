package com.adrianLopez.proyectoPokemon.exception;

public class NotValidCombinationException extends RuntimeException {
    private static final String DESCRIPTION = "Not Valid Combination";

    public NotValidCombinationException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}