package com.unicam.risorseLocali.Core.Controller;

import com.unicam.risorseLocali.Core.Service.MappaService;
import com.unicam.risorseLocali.Exception.GeometryPositionException;
import com.unicam.risorseLocali.Factory.FactoryImplementation.RequestServiceFactoryImpl;
import com.unicam.risorseLocali.Core.Model.Entity.*;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Core.Service.ContributionService;
import com.unicam.risorseLocali.Security.User.UserSecurity;
import com.unicam.risorseLocali.Util.Enum.TypeElementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contribution")
public class ContributionController {
    private final ContributionService contributionService;
    private final RequestServiceFactoryImpl requestServiceFactory;
    private final MappaService mappaService;

    @Autowired
    public ContributionController(ContributionService contributionService,
                                  RequestServiceFactoryImpl requestServiceFactory,
                                  MappaService mappaService) {
        this.contributionService = contributionService;
        this.requestServiceFactory = requestServiceFactory;
        this.mappaService = mappaService;
    }

    @RequestMapping("request/all")
    public List<? extends Richiesta> getAllRichieste(Principal principal) {
        List<? extends Richiesta> listRequest = new ArrayList<>();
        UserSecurity security = (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        for (RichiestaService service : this.requestServiceFactory.createAllServiceRequest()) {
            listRequest.addAll(service.getAllRichiesteByUsername(security.getUsername()));
        }
        return listRequest;
    }

    @RequestMapping("request/{stato}")
    public ResponseEntity<List<? extends Richiesta>> getAllRichiesteFilter(Principal principal, @PathVariable("stato") String stato) {
        List<? extends Richiesta> listRequest = new ArrayList<>();
        UserSecurity security = (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        for (RichiestaService service : this.requestServiceFactory.createAllServiceRequest()) {
            listRequest.addAll(service.getAllRichiesteByUsernameAndStatus(security.getUsername(), stato.toUpperCase()));
        }
        return new ResponseEntity<>(listRequest, HttpStatus.OK);
    }

    @RequestMapping("/requestDetail/{id}")
    public ResponseEntity<List<Object>> getRequestDetails(@PathVariable String id){
        RichiestaService richiestaService = this.requestServiceFactory.createServiceRequestById(id);
        return richiestaService.getRequestDetails(id);
    }

    @PostMapping("/addPOI")
    @Transactional
    public ResponseEntity<Richiesta> addPoi(@RequestBody POI poi, Principal principal) {
        if(this.mappaService.verifyCoordinate(poi.getPosizione())){
            UserSecurity security = (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            this.contributionService.addElementToComune(poi, security.getUser());
            RichiestaService richiestaService = this.requestServiceFactory.createRequestService(poi);
            return richiestaService.addRichiesta(new RichiestaPOI(poi.getId(), TypeElementRequest.INSERT), poi, security.getUser());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addItinerario")
    @Transactional
    public ResponseEntity<Richiesta> addItinerario(@RequestBody Itinerari itinerari, Principal principal) {
        UserSecurity security = (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        this.contributionService.addElementToComune(itinerari, security.getUser());
        RichiestaService richiestaService = this.requestServiceFactory.createRequestService(itinerari);
        return richiestaService.addRichiesta(new RichiestaItinerario(itinerari.getId(), TypeElementRequest.INSERT), itinerari, security.getUser());
    }

    @PutMapping(value = "/modifyPOI/{id}")
    @Transactional
    public ResponseEntity<Richiesta> modifyPOI(@PathVariable String id, @RequestBody POI poi, Principal principal) {
        if(this.mappaService.verifyCoordinate(poi.getPosizione())){
            UserSecurity security = (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            this.contributionService.modifyElementInComune(poi, id, security.getUser());
            poi.setId(id);
            RichiestaService richiestaService = this.requestServiceFactory.createRequestService(poi);
            return richiestaService.addRichiesta(new RichiestaPOI(poi.getId(), TypeElementRequest.MODIFY), poi, security.getUser());
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    @PutMapping(value = "/modifyItinerario/{id}")
    @Transactional
    public ResponseEntity<Richiesta> modifyItinerario(@PathVariable String id, @RequestBody Itinerari itinerari, Principal principal) {
        UserSecurity security = (UserSecurity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        this.contributionService.modifyElementInComune(itinerari, id, security.getUser());
        RichiestaService richiestaService = this.requestServiceFactory.createRequestService(itinerari);
        return richiestaService.addRichiesta(new RichiestaItinerario(id, TypeElementRequest.MODIFY), itinerari, security.getUser());
    }

}

