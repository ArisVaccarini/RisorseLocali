package com.unicam.risorseLocali.Request.ExecuteRequest.Command.ApproveCommand;

import com.unicam.risorseLocali.Request.ExecuteRequest.Command.Command;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Util.Enum.StatusElement;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Questa classi di occupa di accettare le richieste di modifica
 */
public class ApproveModifyCommand implements Command {

    private final RichiestaService richiestaService;
    private final ElementiMappaServiceImpl elementiMappaService;
    private final String idReq;

    public ApproveModifyCommand(RichiestaService richiestaService,
                                ElementiMappaServiceImpl elementiMappaService,
                                String idReq) {
        this.richiestaService = richiestaService;
        this.elementiMappaService = elementiMappaService;
        this.idReq = idReq;
    }

    @Override
    @Transactional
    public void execute() {
        Richiesta richiesta = this.richiestaService.getRichiestaById(this.idReq);
        ElementiMappa element =  this.elementiMappaService.getElement(richiesta.getElementLinked());
        element.changeStatus(StatusElement.VISIBLE);
        this.elementiMappaService.updateElement(element, richiesta.getIdOldElement());
        richiesta.setStato(StatusRequest.APPROVED);
        this.richiestaService.updateRichiesta(richiesta);
    }

}