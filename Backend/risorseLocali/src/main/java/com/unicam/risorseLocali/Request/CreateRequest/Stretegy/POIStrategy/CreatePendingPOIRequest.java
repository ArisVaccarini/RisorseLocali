package com.unicam.risorseLocali.Request.CreateRequest.Stretegy.POIStrategy;

import com.unicam.risorseLocali.Core.Model.Entity.RichiestaPOI;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Repository.RichiestaPOIRepository;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;

/**
 * Questa classe crea la richiesta in pending per i poi
 */
public class CreatePendingPOIRequest implements CreateRequestPOI{

    @Override
    public RichiestaPOI createRichiesta(RichiestaPOI newReq,
                                        User user,
                                        RichiestaPOIRepository typeRepo) {
        newReq.setIdAccount(user.getNomeUtente());
        newReq.setDescrizione("Operazione di " + newReq.getTipo() + " su POI");
        newReq.setStato(StatusRequest.PENDING);
        return typeRepo.save(newReq);
    }

}
