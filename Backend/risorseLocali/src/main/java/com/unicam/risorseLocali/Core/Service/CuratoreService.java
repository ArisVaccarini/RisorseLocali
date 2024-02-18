package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Request.ExecuteRequest.CommandAbstractFactory.CommandApprovedFactory;
import com.unicam.risorseLocali.Request.ExecuteRequest.CommandAbstractFactory.CommandRejectFactory;
import com.unicam.risorseLocali.Request.ExecuteRequest.CommandInvoker;
import com.unicam.risorseLocali.Factory.FactoryImplementation.ElementiMappaServiceFactoryImpl;
import com.unicam.risorseLocali.Factory.FactoryImplementation.RequestServiceFactoryImpl;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe ha il compito di fornire
 * i servizi del curatore, come la validazione delle
 * richieste inoltrate dai contributori.
 */
@Service
public class CuratoreService {

    private final ElementiMappaServiceFactoryImpl elementiMappaService;
    private final RequestServiceFactoryImpl requestServiceFactory;

    @Autowired
    public CuratoreService(ElementiMappaServiceFactoryImpl elementiMappaService,
                           RequestServiceFactoryImpl requestServiceFactory) {
        this.elementiMappaService = elementiMappaService;
        this.requestServiceFactory = requestServiceFactory;
    }

    public List<? extends Richiesta> getAllRequestFromUser(){
        List<Richiesta> listRequest= new ArrayList<>();
        for (RichiestaService service : this.requestServiceFactory.createAllServiceRequest()) {
            listRequest.addAll(service.getAllRichiesteByStatus(StatusRequest.PENDING));
        }
        return listRequest;
    }

    public List<? extends Richiesta> getAllRequestFromUserByOperation(String operation){
        List<? extends Richiesta> list = this.getAllRequestFromUser();
        list.removeIf(richiesta -> !(richiesta.getTipo().equals(operation)));
        return list;
    }

    public ResponseEntity<Richiesta> approveRequest(String idRichiesta){
        return new ResponseEntity<>(this.setAndCallInvoker(idRichiesta,
                new CommandInvoker(new CommandApprovedFactory())), HttpStatus.OK);
    }

    public ResponseEntity<Richiesta> rejectRequest(String idRichiesta){
        return new ResponseEntity<>(this.setAndCallInvoker(idRichiesta,
                        new CommandInvoker(new CommandRejectFactory())), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteElement(String id){
        ElementiMappaServiceImpl elementService = this.elementiMappaService.createElementServiceByIdElement(id);
        elementService.deleteElement(id);
        return new ResponseEntity<>("Elemento con id(" + id + "), eliminato con successo", HttpStatus.OK);
    }

    private Richiesta setAndCallInvoker(String idRichiesta, CommandInvoker invoker){
        RichiestaService richiestaService = this.requestServiceFactory.createServiceRequestById(idRichiesta);
        Richiesta richiesta = richiestaService.getRichiestaById(idRichiesta);
        try {
            ElementiMappaServiceImpl elementService =
                    this.elementiMappaService
                            .createElementServiceByIdElement(richiesta.getIdOldElement());
            invoker.executeCommand(richiestaService,elementService,idRichiesta);
        }
        catch (FetchNotFoundException fetchNotFoundException){
            fetchNotFoundException.getStackTrace();
        }
        return richiesta;
    }

}
