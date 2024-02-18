package com.unicam.risorseLocali.Core.Controller;

import com.unicam.risorseLocali.Factory.FactoryImplementation.RequestServiceFactoryImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Service.CuratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curatore")
public class CuratoreController {

    private final CuratoreService curatoreService;
    private final RequestServiceFactoryImpl requestServiceFactory;

    @Autowired
    public CuratoreController(CuratoreService curatoreService, RequestServiceFactoryImpl requestServiceFactory) {
        this.curatoreService = curatoreService;
        this.requestServiceFactory = requestServiceFactory;
    }

    @RequestMapping("/all")
    public Iterable<? extends Richiesta> getAllRichieste(){
       return this.curatoreService.getAllRequestFromUser();
    }

    @RequestMapping("/all/{operation}")
    public Iterable<? extends Richiesta> getAllRichiesteByOperation(@PathVariable String operation){
        return this.curatoreService.getAllRequestFromUserByOperation(operation.toUpperCase());
    }

    @RequestMapping("/requestDetail/{id}")
    public ResponseEntity<List<Object>> getRequestDetails(@PathVariable String id){
        RichiestaService richiestaService = this.requestServiceFactory.createServiceRequestById(id);
        return richiestaService.getRequestDetails(id);
    }

    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<Richiesta> approveRequest(@PathVariable String id) {
        return this.curatoreService.approveRequest(id);
    }

    @PutMapping(value = "/reject/{id}")
    public ResponseEntity<Richiesta> rejectRequest(@PathVariable String id) {
        return this.curatoreService.rejectRequest(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteElement(@PathVariable String id) {
        return this.curatoreService.deleteElement(id);
    }
}
