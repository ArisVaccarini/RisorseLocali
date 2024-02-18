package com.unicam.risorseLocali.Factory.FactoryInterface;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;

import java.util.List;

public interface RequestServiceFactory {

    RichiestaService createRequestService(ElementiMappa elementiMappa);

    RichiestaService createServiceRequestById(String id);

    List<RichiestaService> createAllServiceRequest();

}
