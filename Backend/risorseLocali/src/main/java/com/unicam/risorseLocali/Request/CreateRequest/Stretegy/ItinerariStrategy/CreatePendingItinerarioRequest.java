package com.unicam.risorseLocali.Request.CreateRequest.Stretegy.ItinerariStrategy;

import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaItinerario;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Repository.RichiestaItinerarioRepository;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;

/**
 * Questa classe crea la richiesta in pending per gli itinerari
 */
public class CreatePendingItinerarioRequest implements CreateRequestItinerari{

    @Override
    public Richiesta createRichiesta(RichiestaItinerario newReq,
                                     User user,
                                     RichiestaItinerarioRepository typeRepo) {
        newReq.setIdAccount(user.getNomeUtente());
        newReq.setDescrizione("Operazione di " + newReq.getTipo() + " su Itinerario");
        newReq.setStato(StatusRequest.PENDING);
        return typeRepo.save(newReq);
    }
}
