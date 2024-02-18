package com.unicam.risorseLocali.Request.ExecuteRequest.Command.RejectCommand;

import com.unicam.risorseLocali.Request.ExecuteRequest.Command.Command;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;
import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;

/**
 * Questa classi di occupa di rifiutare le richieste di modifica
 */
public class RejectModifyCommand implements Command {

    private final RichiestaService richiestaService;
    private final ElementiMappaServiceImpl elementiMappaService;
    private final String idReq;

    public RejectModifyCommand(RichiestaService richiestaService, ElementiMappaServiceImpl elementiMappaService, String idReq) {
        this.richiestaService = richiestaService;
        this.elementiMappaService = elementiMappaService;
        this.idReq = idReq;
    }

    @Override
    public void execute() {
        Richiesta richiesta = this.richiestaService.getRichiestaById(this.idReq);
        ElementiMappa element =  this.elementiMappaService.getElement(richiesta.getElementLinked());
        this.elementiMappaService.deleteElement(element.getId());
        richiesta.setStato(StatusRequest.REJECTED);
        this.richiestaService.updateRichiesta(richiesta);
    }

}
