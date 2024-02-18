package com.unicam.risorseLocali.Exception;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(String idNotFound) {
        super("Impossibile trovare l'elemento con id (" + idNotFound + ")");
    }
}
