package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Core.Model.Entity.Itinerari;
import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaItinerario;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Repository.RichiestaItinerarioRepository;
import com.unicam.risorseLocali.Exception.RequestNotFoundException;
import com.unicam.risorseLocali.Request.CreateRequest.Stretegy.ItinerariStrategy.StrategyItinerariChooser;
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
 * relativi alle richieste riguardanti gli itinerari.
 */
@Service("ItinerarioRichiestaService")
public class RichiestaItinerariService implements RichiestaService<RichiestaItinerario> {

    private final RichiestaItinerarioRepository richiestaRepository;
    private final StrategyItinerariChooser strategyItinerari ;
    private final ItinerariService itinerariService;

    @Autowired
    public RichiestaItinerariService(RichiestaItinerarioRepository richiestaRepository,
                                     StrategyItinerariChooser strategyItinerari,
                                     ItinerariService itinerariService) {
        this.richiestaRepository = richiestaRepository;
        this.strategyItinerari = strategyItinerari;
        this.itinerariService = itinerariService;
    }

    @Override
    public List<RichiestaItinerario> getAllRichieste() {
        return richiestaRepository.findAll();
    }

    @Override
    public RichiestaItinerario getRichiestaById(String id) {
        return this.richiestaRepository.findById(id).orElse(null);
    }

    @Override
    public List<RichiestaItinerario> getAllRichiesteByUsername(String username){
        return this.richiestaRepository.findByIdAccount(username);
    }

    @Override
    public List<RichiestaItinerario> getAllRichiesteByStatus(StatusRequest status) {
        return this.richiestaRepository.findByStato(status);
    }

    @Override
    public ResponseEntity<Richiesta> addRichiesta(RichiestaItinerario richiestaItinerario,
                                  ElementiMappa elementiMappa, User user) {
        return new ResponseEntity<>(this.strategyItinerari.createRequest(richiestaItinerario, elementiMappa, user)
                .createRichiesta(richiestaItinerario, user, this.richiestaRepository), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RichiestaItinerario> updateRichiesta(RichiestaItinerario richiesta) {
        if(this.richiestaRepository.existsById(richiesta.getId())){
            this.richiestaRepository.save(richiesta);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new RequestNotFoundException(richiesta.getId());
    }

    @Override
    public List<RichiestaItinerario> getAllRichiesteByUsernameAndStatus(String username, String status){
        List<RichiestaItinerario> list = this.richiestaRepository.findByIdAccount(username);
        list.removeIf(richiestaItinerario -> !(richiestaItinerario.getStato().toString().equals(status)));
        return list;
    }

    @Override
    public ResponseEntity<List<Object>> getRequestDetails(String idRichiesta) {
        Optional<RichiestaItinerario> optional = this.richiestaRepository.findById(idRichiesta);
        RichiestaItinerario richiestaItinerario =
                optional.orElseThrow(() -> new RequestNotFoundException(idRichiesta));
        Itinerari itinerari = this.itinerariService.getElement(richiestaItinerario.getIdOldElement());
        if(richiestaItinerario.getLinkItinerario() != null)
            return new ResponseEntity<>(Arrays.asList(richiestaItinerario,itinerari,
                    (this.itinerariService
                            .getElement(richiestaItinerario.getLinkItinerario()
                                    .getIdElement()))), HttpStatus.OK);
        return new ResponseEntity<>(Arrays.asList(richiestaItinerario, itinerari), HttpStatus.OK);
    }

}
