package com.unicam.risorseLocali.Request.CreateRequest.Stretegy.ItinerariStrategy;

import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaItinerario;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Repository.RichiestaItinerarioRepository;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;

/**
 * Questa classe si occupa della creazione
 * della richiesta autorizzata per gli itinerari
 */
public class CreateAuthItinerarioRequest implements CreateRequestItinerari{

    @Override
    public Richiesta createRichiesta(RichiestaItinerario newReq,
                                     User user,
                                     RichiestaItinerarioRepository typeRepo) {
        newReq.setIdAccount(user.getNomeUtente());
        newReq.setDescrizione("Operazione di " + newReq.getTipo() + " su Itinerario");
        newReq.setStato(StatusRequest.APPROVED);
        return typeRepo.save(newReq);
    }
}
