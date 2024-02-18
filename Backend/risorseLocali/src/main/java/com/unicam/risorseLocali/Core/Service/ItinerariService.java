package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Entity.Itinerari;
import com.unicam.risorseLocali.Core.Repository.ItinerariRepository;
import com.unicam.risorseLocali.Core.Model.Entity.POI;
import com.unicam.risorseLocali.Exception.ElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Questa classe ha il compito di fornire i servizi
 * relativi agli itinerari.
 */
@Service("itinerariService")
public class ItinerariService implements ElementiMappaServiceImpl<Itinerari> {

    private final ItinerariRepository itinerariRepository;

    @Autowired
    public ItinerariService(ItinerariRepository itinerariRepository) {
        this.itinerariRepository = itinerariRepository;
    }

    @Override
    public List<Itinerari> getAllElement() {
        return this.itinerariRepository.findAll();
    }

    @Override
    public Itinerari getElement(String id) {
        return this.itinerariRepository.findById(id).orElse(null);
    }

    @Transactional
    public Itinerari addElement(Itinerari itinerario) {
        this.itinerariRepository.save(itinerario);
        return itinerario;
    }

    public Itinerari updateElement(Itinerari itinerari, String id) {
            if(this.itinerariRepository.existsById(id)){
                this.updateExistingNewItinerari(itinerari, id);
                itinerari.setId(id);
                return this.itinerariRepository.save(itinerari);
            }
            throw new ElementNotFoundException(id);
    }

    private Itinerari updateExistingNewItinerari(Itinerari itinerari, String id){
        Optional<Itinerari> oldItin = this.itinerariRepository.findById(id);
        if (this.itinerariRepository.existsById(itinerari.getId()) && !(itinerari.getId().equals(id))){
            oldItin.get().setPercorso(itinerari.getPercorso());
            Set<POI> poiSet = itinerari.getPercorso();
            this.itinerariRepository.deleteById(itinerari.getId());
            oldItin.get().setPercorso(poiSet);
            this.itinerariRepository.save(oldItin.get());
        }
        return itinerari;
    }

    public void deleteElement(String id) {
        if(this.itinerariRepository.existsById(id))
            this.itinerariRepository.deleteById(id);
        else
            throw new ElementNotFoundException(id);
    }

}