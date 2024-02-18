package com.unicam.risorseLocali.Util;

import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Util.Enum.StatusElement;

public class SelectorOperationUser {

    public ElementiMappa selectStatusElementByUser(ElementiMappa element, User user){
        switch(user.getRuolo()){
            case CONTR -> element.changeStatus(StatusElement.PENDING);
            case AUTH_CONTR, CURATORE -> element.changeStatus(StatusElement.VISIBLE);
        }
        return element;
    }
}
