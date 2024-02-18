package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Factory.FactoryImplementation.ElementiMappaServiceFactoryImpl;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Util.SelectorOperationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Questa classe ha il compito di fornire i servizi
 * per la contrubuzione
 */
@Service
public class ContributionService {

    private final ElementiMappaServiceFactoryImpl elementServiceFactory;

    @Autowired
    public ContributionService(ElementiMappaServiceFactoryImpl elementServiceFactory) {
        this.elementServiceFactory = elementServiceFactory;
    }

    public ResponseEntity<ElementiMappa> addElementToComune(ElementiMappa elementiMappa, User user){
        ElementiMappaServiceImpl elementiMappaService = this.elementServiceFactory.createElementService(elementiMappa);
        return new ResponseEntity<>(elementiMappaService.addElement(new SelectorOperationUser()
                .selectStatusElementByUser(elementiMappa, user)), HttpStatus.OK);
    }

    public ResponseEntity<ElementiMappa> modifyElementInComune(ElementiMappa elementiMappa, String id, User user){
        ElementiMappaServiceImpl elementiMappaService = this.elementServiceFactory.createElementService(elementiMappa);
        return new ResponseEntity<>(elementiMappaService.updateElement(new SelectorOperationUser()
                .selectStatusElementByUser(elementiMappa,user), id),HttpStatus.OK);
    }

}