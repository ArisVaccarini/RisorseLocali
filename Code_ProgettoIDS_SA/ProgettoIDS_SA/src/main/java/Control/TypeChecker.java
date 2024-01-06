package Control;

import Model.ElementiMappa;

public class TypeChecker {

    public TypeChecker() {
    }

    public String checkTipo(ElementiMappa newElem){
        if (newElem.getClass().getSimpleName().equals("POI"))
            return "richieste_poi";
        else
            return "richieste_itinerari";
    }
}
