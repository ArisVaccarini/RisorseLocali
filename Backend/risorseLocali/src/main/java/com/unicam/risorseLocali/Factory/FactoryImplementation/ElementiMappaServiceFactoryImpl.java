package com.unicam.risorseLocali.Factory.FactoryImplementation;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Service.ItinerariService;
import com.unicam.risorseLocali.Core.Service.POIService;
import com.unicam.risorseLocali.Exception.RequestNotFoundException;
import com.unicam.risorseLocali.Factory.FactoryInterface.ElementiMappaServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Questa classe ha la responsabilita di fornire diversi metodi
 * di creazione di servizi elementi mappa
 */
@Component
public class ElementiMappaServiceFactoryImpl implements ElementiMappaServiceFactory {

    private final List<ElementiMappaServiceImpl> elementiMappaServiceList;

    @Autowired
    public ElementiMappaServiceFactoryImpl(List<ElementiMappaServiceImpl> elementiMappaServiceList) {
        this.elementiMappaServiceList = elementiMappaServiceList;
    }

    @Override
    public ElementiMappaServiceImpl createElementService(ElementiMappa elementiMappa) {
        return switch (elementiMappa.getClass().getSimpleName()){
            case "POI" -> getElementService(POIService.class);
            case "Itinerari" -> getElementService(ItinerariService.class);
            default -> throw new IllegalArgumentException("Elemento non valido");
        };
    }

    @Override
    public ElementiMappaServiceImpl createElementServiceByIdElement(String id) {
        for (ElementiMappaServiceImpl service : this.elementiMappaServiceList) {
            if(service.getElement(id) != null)
                return service;
        }
        throw new RequestNotFoundException(id);
    }

    @Override
    public ElementiMappaServiceImpl createElementServiceByServiceName(Class service) {
        return getElementService(service);
    }

    @Override
    public List<ElementiMappaServiceImpl> createAllElementService() {
        return this.elementiMappaServiceList;
    }

    private ElementiMappaServiceImpl getElementService(Class type){
        return (ElementiMappaServiceImpl) this.elementiMappaServiceList.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst()
                .orElse(null);
    }

}