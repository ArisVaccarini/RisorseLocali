package com.unicam.risorseLocali.Request.ExecuteRequest.CommandAbstractFactory;

import com.unicam.risorseLocali.Request.ExecuteRequest.Command.Command;
import com.unicam.risorseLocali.Request.ExecuteRequest.Command.RejectCommand.RejectInsertCommand;
import com.unicam.risorseLocali.Request.ExecuteRequest.Command.RejectCommand.RejectModifyCommand;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;

/**
 * Questa classe ha il compito di creare i comandi
 * per il rifiuto delle richieste
 */
public class CommandRejectFactory implements CommandFactory{

    @Override
    public Command createInsertCommand(RichiestaService richiestaService,
                                       ElementiMappaServiceImpl elementiMappaService,
                                       String idReq) {
        return new RejectInsertCommand(richiestaService, elementiMappaService, idReq);
    }

    @Override
    public Command createModifyCommand(RichiestaService richiestaService,
                                       ElementiMappaServiceImpl elementiMappaService,
                                       String idReq) {
        return new RejectModifyCommand(richiestaService, elementiMappaService, idReq);
    }

}