package com.unicam.risorseLocali.Core.Repository;

import com.unicam.risorseLocali.Core.Model.Entity.RichiestaPOI;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RichiestaPOIRepository extends RichiestaRepository<RichiestaPOI>{

    @Override
    List<RichiestaPOI> findByIdAccount(String username);

    @Override
    List<RichiestaPOI> findByStato(StatusRequest status);

}
