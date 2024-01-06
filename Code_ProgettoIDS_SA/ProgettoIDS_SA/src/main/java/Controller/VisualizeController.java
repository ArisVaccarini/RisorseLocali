package Controller;

import Model.ElementiMappa;
import Model.Itinerari;
import Model.MappaComune;
import Model.POI;
import Recources.Coordinate;
import java.util.ArrayList;

/**
 * Questa classe ha il compito di gestire tutte le operazioni
 * che riguardano la visualizzazione di un determinato elemento
 * della mappa del comune
 */
public class VisualizeController {
    private MappaComune mappaComune;

    public VisualizeController() {
        this.mappaComune = new MappaComune();
    }

    public MappaComune getMappaComune() {
        return mappaComune;
    }

    /**
     * Filtra la mappa, in base al nome della categoria
     * di elementi da visualizzare
     *
     * @param conditionFilter il nome della categoria per cui filtrare
     * @return la lista degli elementi restituiti dalla mappa del comune
     */
    public ArrayList<? extends ElementiMappa> filter(String conditionFilter){
        return switch (conditionFilter){
            case "1" -> this.mappaComune.getTypedComponent(POI.class);
            case "2" -> this.mappaComune.getTypedComponent(Itinerari.class);
            default -> null;
        };
    }

}
