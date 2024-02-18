package com.unicam.risorseLocali.Request.CreateRequest.Stretegy.POIStrategy;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Entity.RequestLinkPoi;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaPOI;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Util.Enum.Role;
import com.unicam.risorseLocali.Util.Enum.TypeElementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Questa classe rimanda alle apposite classi di creazioni di richieste
 * dei poi basandosi sul ruolo dell'utente che la effettua
 */
@Component
public class StrategyPOIChooser {

    @Autowired
    public StrategyPOIChooser() {
    }

    public CreateRequestPOI createRequest(RichiestaPOI richiestaPOI,
                                          ElementiMappa elementiMappa,
                                          User user) {
        return this.getStrategyRequest(richiestaPOI, elementiMappa, user.getRuolo());
    }

    private CreateRequestPOI getStrategyRequest(RichiestaPOI richiesta,ElementiMappa elementiMappa, Role role) {
        return switch (role) {
            case CONTR -> this.existModifyDetails(richiesta, elementiMappa);
            case AUTH_CONTR, CURATORE -> new CreateAuthPOIRequest();
            default -> throw new IllegalArgumentException("Classe inesistente");
        };
    }

    private CreateRequestPOI existModifyDetails(RichiestaPOI richiesta, ElementiMappa elementiMappa){
        if(richiesta.getTipo().equals(TypeElementRequest.MODIFY))
            richiesta.setLinkPoi(new RequestLinkPoi(elementiMappa.getId(), richiesta));
        return new CreatePendingPOIRequest();
    }

}
