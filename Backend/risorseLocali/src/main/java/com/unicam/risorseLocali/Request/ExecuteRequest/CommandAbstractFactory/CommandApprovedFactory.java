package com.unicam.risorseLocali.Request.ExecuteRequest.CommandAbstractFactory;

import com.unicam.risorseLocali.Request.ExecuteRequest.Command.ApproveCommand.ApproveInsertCommand;
import com.unicam.risorseLocali.Request.ExecuteRequest.Command.ApproveCommand.ApproveModifyCommand;
import com.unicam.risorseLocali.Request.ExecuteRequest.Command.Command;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;

/**
 * Questa classe ha il compito di creare i comandi
 * per l'accettazione delle richieste
 */
public class CommandApprovedFactory implements CommandFactory{

    @Override
    public Command createInsertCommand(RichiestaService richiestaService,
                                       ElementiMappaServiceImpl elementiMappaService,
                                       String idReq) {
        return new ApproveInsertCommand(richiestaService,elementiMappaService,idReq);
    }

    @Override
    public Command createModifyCommand(RichiestaService richiestaService,
                                       ElementiMappaServiceImpl elementiMappaService,
                                       String idReq) {
        return new ApproveModifyCommand(richiestaService,elementiMappaService,idReq);
    }

}