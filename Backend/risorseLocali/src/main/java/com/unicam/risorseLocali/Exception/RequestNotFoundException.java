package com.unicam.risorseLocali.Exception;

public class RequestNotFoundException extends RuntimeException{

    public RequestNotFoundException(String idReq) {
        super("Impossibile trovare la richiesta con id (" + idReq + ")");
    }
}
