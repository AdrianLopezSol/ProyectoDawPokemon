package com.adrianLopez.proyectoPokemon.common.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.adrianLopez.proyectoPokemon.common.exception.BadRequestException;
import com.adrianLopez.proyectoPokemon.common.exception.DBConnectionException;
import com.adrianLopez.proyectoPokemon.common.exception.NotValidCombinationException;
import com.adrianLopez.proyectoPokemon.common.exception.ResourceNotFoundException;
import com.adrianLopez.proyectoPokemon.common.exception.SQLStatementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            NotValidCombinationException.class
    })
    @ResponseBody
    public ErrorMessage conflictOnOperation(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        BadRequestException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpMessageNotReadableException exception) {
        return new ErrorMessage("Invalid request body", HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            SQLStatementException.class
    })
    @ResponseBody
    public ErrorMessage SQLException(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("SQL exception", HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            DBConnectionException.class,
    })
    @ResponseBody
    public ErrorMessage DBException(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("DB timeout exception", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class,
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("Internal error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
