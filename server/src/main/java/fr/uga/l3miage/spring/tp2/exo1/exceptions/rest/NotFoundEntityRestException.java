package fr.uga.l3miage.spring.tp2.exo1.exceptions.rest;

import lombok.Getter;

@Getter
public class NotFoundEntityRestException extends RuntimeException{

    public NotFoundEntityRestException(String message) {
        super(message);
    }
}
