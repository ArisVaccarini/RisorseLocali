package com.unicam.risorseLocali.Request.CreateRequest.Stretegy.ItinerariStrategy;

import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Model.Entity.RichiestaItinerario;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Repository.RichiestaItinerarioRepository;

public interface CreateRequestItinerari {

    Richiesta createRichiesta(RichiestaItinerario richiestaItinerario, User user,
                              RichiestaItinerarioRepository typeRepo);

}
