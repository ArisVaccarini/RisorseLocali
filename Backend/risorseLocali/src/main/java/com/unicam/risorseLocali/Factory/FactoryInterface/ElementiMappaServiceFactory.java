package com.unicam.risorseLocali.Factory.FactoryInterface;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;

import java.util.List;

public interface ElementiMappaServiceFactory {

    List<ElementiMappaServiceImpl> createAllElementService();
    ElementiMappaServiceImpl createElementService(ElementiMappa elementiMappa);
    ElementiMappaServiceImpl createElementServiceByIdElement(String id);
    ElementiMappaServiceImpl createElementServiceByServiceName(Class service);
}
