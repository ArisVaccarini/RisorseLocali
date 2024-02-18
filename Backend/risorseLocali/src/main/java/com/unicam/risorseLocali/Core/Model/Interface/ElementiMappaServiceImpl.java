package com.unicam.risorseLocali.Core.Model.Interface;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ElementiMappaServiceImpl<E extends ElementiMappa> {

    List<? extends ElementiMappa> getAllElement();

    ElementiMappa getElement(String id);

    E addElement(E itinerario);

    E updateElement(E element, String idRef);

    void deleteElement(String id);


}
