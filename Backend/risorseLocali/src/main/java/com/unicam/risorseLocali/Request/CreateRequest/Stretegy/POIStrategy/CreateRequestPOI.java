package com.unicam.risorseLocali.Request.CreateRequest.Stretegy.POIStrategy;

import com.unicam.risorseLocali.Core.Model.Entity.RichiestaPOI;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Repository.RichiestaPOIRepository;

public interface CreateRequestPOI {

    RichiestaPOI createRichiesta(RichiestaPOI richiestaPOI, User user, RichiestaPOIRepository typeRepo);

}
