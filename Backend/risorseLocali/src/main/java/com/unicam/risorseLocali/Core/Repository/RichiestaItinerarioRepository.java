package com.unicam.risorseLocali.Core.Repository;

import com.unicam.risorseLocali.Core.Model.Entity.RichiestaItinerario;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RichiestaItinerarioRepository extends RichiestaRepository<RichiestaItinerario>{

    @Override
    List<RichiestaItinerario> findByIdAccount(String username);

    @Override
    List<RichiestaItinerario> findByStato(StatusRequest status);
}
