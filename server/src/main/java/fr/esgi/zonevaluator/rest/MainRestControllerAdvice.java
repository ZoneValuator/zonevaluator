package fr.esgi.zonevaluator.rest;

import fr.esgi.zonevaluator.exception.ParametreManquantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MainRestControllerAdvice {

    @ExceptionHandler(ParametreManquantException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleParametreManquantException(ParametreManquantException e) {
        return e.getMessage();
    }

}
