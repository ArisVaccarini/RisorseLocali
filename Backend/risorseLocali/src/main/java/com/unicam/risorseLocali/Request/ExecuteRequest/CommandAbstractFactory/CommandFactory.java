package com.unicam.risorseLocali.Request.ExecuteRequest.CommandAbstractFactory;

import com.unicam.risorseLocali.Request.ExecuteRequest.Command.Command;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappaServiceImpl;
import com.unicam.risorseLocali.Core.Model.Interface.RichiestaService;

public interface CommandFactory {

    Command createInsertCommand(RichiestaService richiestaService,
                                ElementiMappaServiceImpl elementiMappaService,
                                String idReq);

    Command createModifyCommand(RichiestaService richiestaService,
                                ElementiMappaServiceImpl elementiMappaService,
                                String idReq);

}
