package com.unicam.risorseLocali.Factory.FactoryImplementation;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Core.Service.RichiestaItinerariService;
import com.unicam.risorseLocali.Core.Service.RichiestaPOIService;
import com.unicam.risorseLocali.Exception.RequestNotFoundException;
import com.unicam.risorseLocali.Factory.FactoryInterface.RequestServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Questa classe ha la responsabilita di fornire diversi metodi
 * di creazione per i servizi legati alle richieste
 */
@Component
public class RequestServiceFactoryImpl implements RequestServiceFactory {

    private final List<RichiestaService> richiestaServiceList;

    @Autowired
    public RequestServiceFactoryImpl(List<RichiestaService> elementiMappaServiceList) {
        this.richiestaServiceList = elementiMappaServiceList;
    }


    @Override
    public RichiestaService createRequestService(ElementiMappa elementiMappa) {
        return switch (elementiMappa.getClass().getSimpleName()){
            case "POI" -> getRequestService(RichiestaPOIService.class);
            case "Itinerari" -> getRequestService(RichiestaItinerariService.class);
            default -> throw new IllegalArgumentException("Elemento non valido");
        };
    }

    @Override
    public RichiestaService createServiceRequestById(String id) {
        for (RichiestaService service : this.richiestaServiceList) {
            if(service.getRichiestaById(id) != null)
                return service;
        }
        throw new RequestNotFoundException(id);
    }

    @Override
    public List<RichiestaService> createAllServiceRequest() {
        return this.richiestaServiceList;
    }

    private RichiestaService getRequestService(Class type){
        return (RichiestaService) this.richiestaServiceList.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst()
                .orElse(null);
    }

}
