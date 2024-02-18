package com.unicam.risorseLocali.Request.ExecuteRequest;

import com.unicam.risorseLocali.Request.ExecuteRequest.Command.Command;
import com.unicam.risorseLocali.Request.ExecuteRequest.CommandAbstractFactory.CommandFactory;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;

/**
 * Questa classe si occupa di invocare i vari comandi
 * per l'accettazione/riufiuto delle richieste
 */
public class CommandInvoker {

    private final CommandFactory commandFactory;

    public CommandInvoker(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void executeCommand(RichiestaService requestService,
                               ElementiMappaServiceImpl elementiMappaService,
                               String idReq){
        this.getCommandToExecute(requestService, elementiMappaService, idReq).execute();
    }

    private Command getCommandToExecute(RichiestaService requestService,
                                        ElementiMappaServiceImpl elementiMappaService,
                                        String idRef){
        return switch (requestService.getRichiestaById(idRef).getTipo()){
            case INSERT -> commandFactory.createInsertCommand(requestService, elementiMappaService, idRef);
            case MODIFY -> commandFactory.createModifyCommand(requestService, elementiMappaService, idRef);
        };
    }

}