package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaPOI;
import com.unicam.risorseLocali.Core.Repository.RichiestaPOIRepository;
import com.unicam.risorseLocali.Core.Model.Entity.POI;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Exception.RequestNotFoundException;
import com.unicam.risorseLocali.Request.CreateRequest.Stretegy.POIStrategy.StrategyPOIChooser;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Questa classe ha il compito di fornire i servizi
 * relativi alle richieste riguardanti i poi.
 */
@Service("POIRichiestaService")
public class RichiestaPOIService implements RichiestaService<RichiestaPOI> {

    private final RichiestaPOIRepository richiestaRepository;
    private final POIService poiService;
    private final StrategyPOIChooser strategyPOI;

    @Autowired
    public RichiestaPOIService(RichiestaPOIRepository richiestaRepository, StrategyPOIChooser strategyPOI, POIService poiService) {
        this.richiestaRepository = richiestaRepository;
        this.strategyPOI = strategyPOI;
        this.poiService = poiService;
    }

    @Override
    public List<RichiestaPOI> getAllRichieste() {
        return this.richiestaRepository.findAll();
    }

    @Override
    public RichiestaPOI getRichiestaById(String id) {
            return this.richiestaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<Richiesta> addRichiesta(RichiestaPOI richiestaPOI, ElementiMappa elementiMappa, User user) {
        return new ResponseEntity<>(this.strategyPOI.createRequest(richiestaPOI, elementiMappa, user)
                .createRichiesta(richiestaPOI, user, this.richiestaRepository),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RichiestaPOI> updateRichiesta(RichiestaPOI richiesta) {
        if(this.richiestaRepository.existsById(richiesta.getId())){
            this.richiestaRepository.save(richiesta);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new RequestNotFoundException(richiesta.getId());
    }

    @Override
    public List<RichiestaPOI> getAllRichiesteByUsername(String username){
        return this.richiestaRepository.findByIdAccount(username);
    }

    @Override
    public List<RichiestaPOI> getAllRichiesteByStatus(StatusRequest status) {
        return this.richiestaRepository.findByStato(status);
    }

    @Override
    public List<RichiestaPOI> getAllRichiesteByUsernameAndStatus(String username, String status){
        List<RichiestaPOI> list = this.richiestaRepository.findByIdAccount(username);
        list.removeIf(richiestaPOI -> !(richiestaPOI.getStato().toString().equals(status)));
        return list;
    }

    @Override
    public ResponseEntity<List<Object>> getRequestDetails(String idRichiesta){
        Optional<RichiestaPOI> optional = this.richiestaRepository.findById(idRichiesta);
        RichiestaPOI richiestaPOI = optional.orElseThrow(() -> new RequestNotFoundException(idRichiesta));;
        POI poi = this.poiService.getElement(richiestaPOI.getIdOldElement());
        if(richiestaPOI.getLinkPoi() != null)
            return new ResponseEntity<>(Arrays.asList(richiestaPOI,poi,
                    (this.poiService
                            .getElement(richiestaPOI.getLinkPoi()
                                    .getIdElement()))), HttpStatus.OK);
        return new ResponseEntity<>(Arrays.asList(richiestaPOI, poi), HttpStatus.OK);
    }

}