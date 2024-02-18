package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Exception.ElementNotFoundException;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Repository.POIRepository;
import com.unicam.risorseLocali.Core.Model.Entity.POI;
import com.unicam.risorseLocali.Exception.GeometryPositionException;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import com.unicam.risorseLocali.Util.Enum.TypeElementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Questa classe ha il compito di fornire i servizi
 * relativi ai poi.
 */
@Service("poiService")
public class POIService implements ElementiMappaServiceImpl<POI> {
    @Autowired
    private final POIRepository poiRepository;

    public POIService(POIRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    public List<POI> getAllElement() {
        return this.poiRepository.findAll();
    }

    public POI getElement(String id) {
        return this.poiRepository.findById(id).orElse(null);
    }

    public POI addElement(POI poi) {
        return this.poiRepository.save(poi);
    }

    public POI updateElement(POI poi, String id) {
        if(this.poiRepository.existsById(id)){
            this.updateExistingNewPOI(poi, id);
            poi.setId(id);
            return this.poiRepository.save(poi);
        }
        throw new ElementNotFoundException(id);
    }

    private POI updateExistingNewPOI(POI poi, String id){
        if (this.poiRepository.existsById(poi.getId()) && !(poi.getId().equals(id)))
            this.poiRepository.deleteById(poi.getId());
        return poi;
    }

    public void deleteElement(String id) {
        if(this.poiRepository.existsById(id))
            this.poiRepository.deleteById(id);
        else
            throw new ElementNotFoundException(id);
    }

}