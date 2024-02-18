package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Control.GeometryPositionVerify;
import com.unicam.risorseLocali.Core.Model.Entity.Coordinate;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaItinerario;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Exception.ElementNotFoundException;
import com.unicam.risorseLocali.Exception.GeometryPositionException;
import com.unicam.risorseLocali.Factory.FactoryImplementation.ElementiMappaServiceFactoryImpl;
import com.unicam.risorseLocali.Util.Enum.StatusElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe ha il compito di fornire i servizi
 * relativi alla mappa del comune di riferimento.
 */
@Service
public class MappaService {

    private final ElementiMappaServiceFactoryImpl elementiMappaService;

    @Autowired
    public MappaService(ElementiMappaServiceFactoryImpl elementiMappaService) {
        this.elementiMappaService = elementiMappaService;
    }

    public List<ElementiMappa> getAllElementInComune(){
        List<ElementiMappa> list = new ArrayList<>();
        for (ElementiMappaServiceImpl service : this.elementiMappaService.createAllElementService()) {
            list.addAll(service.getAllElement());
        }
        list.removeIf(elementiMappa -> !(elementiMappa.getStato().equals(StatusElement.VISIBLE)));
        return list;
    }
    public List<ElementiMappa> getAllTypedElement(Class serviceType) {
        List<ElementiMappa> list = this.elementiMappaService.createElementServiceByServiceName(serviceType).getAllElement();
        list.removeIf(elementiMappa -> !(elementiMappa.getStato().equals(StatusElement.VISIBLE)));
        return list;
    }

    public ResponseEntity<ElementiMappa> getSpecificElement(String id){
        for (ElementiMappaServiceImpl service : this.elementiMappaService.createAllElementService()) {
            if(service.getElement(id) != null)
                return new ResponseEntity<>(service.getElement(id), HttpStatus.OK);
        }
        throw new ElementNotFoundException(id);
    }

    public boolean verifyCoordinate(Coordinate coordinate){
        ElementiMappaServiceImpl service = this.elementiMappaService.createElementServiceByServiceName(POIService.class);
        GeometryPositionVerify geometryPositionVerify= new GeometryPositionVerify();
        return geometryPositionVerify.verifyPointInComune(coordinate.getLatitudine(), coordinate.getLongitudine())
                && geometryPositionVerify.verifyIfCoordinateExist(service.getAllElement(),coordinate);
    }

}
