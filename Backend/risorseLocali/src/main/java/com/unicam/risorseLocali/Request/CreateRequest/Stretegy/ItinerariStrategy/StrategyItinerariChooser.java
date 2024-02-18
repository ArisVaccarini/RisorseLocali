package com.unicam.risorseLocali.Request.CreateRequest.Stretegy.ItinerariStrategy;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Entity.RequestLinkItinerario;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaItinerario;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Util.Enum.Role;
import com.unicam.risorseLocali.Util.Enum.TypeElementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Questa classe rimanda alle apposite classi di creazioni di richieste
 * degli itinerari basandosi sul ruolo dell'utente che la effettua
 */
@Component
public class StrategyItinerariChooser {

    @Autowired
    public StrategyItinerariChooser() {
    }

    public CreateRequestItinerari createRequest(RichiestaItinerario richiestaItinerario,
                                                ElementiMappa elementiMappa,
                                                User user) {
        return this.getStrategyRequest(richiestaItinerario, elementiMappa, user.getRuolo());
    }

    private CreateRequestItinerari getStrategyRequest(RichiestaItinerario richiesta, ElementiMappa elementiMappa, Role role) {
        return switch (role) {
            case CONTR -> this.existModifyDetails(richiesta, elementiMappa);
            case AUTH_CONTR, CURATORE -> new CreateAuthItinerarioRequest();
            default -> throw new IllegalArgumentException("Classe inesistente");
        };
    }

    private CreateRequestItinerari existModifyDetails(RichiestaItinerario richiesta, ElementiMappa elementiMappa){
        if(richiesta.getTipo().equals(TypeElementRequest.MODIFY))
            richiesta.setLinkItinerario(new RequestLinkItinerario(elementiMappa.getId(), richiesta));
        return new CreatePendingItinerarioRequest();
    }

}
