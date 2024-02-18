package com.unicam.risorseLocali.Core.Model.Interface;

import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RichiestaService<R extends Richiesta> {

    List<R> getAllRichieste();
    R getRichiestaById(String id);
    List<R> getAllRichiesteByUsername(String username);
    List<R> getAllRichiesteByStatus(StatusRequest status);
    List<R> getAllRichiesteByUsernameAndStatus(String username, String status);
    ResponseEntity<List<Object>> getRequestDetails(String idRichiesta);
    ResponseEntity<Richiesta> addRichiesta (R richiesta, ElementiMappa elementiMappa, User user);
    ResponseEntity<R> updateRichiesta(R richiesta);

}
