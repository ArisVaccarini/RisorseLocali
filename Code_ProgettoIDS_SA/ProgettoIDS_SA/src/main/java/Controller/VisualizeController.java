package Controller;

import Model.MappaComune;
import Model.POI;
import Recources.Coordinate;
import java.util.ArrayList;

/**
 * Questa classe ha il compiro di gestire tutte le operazioni
 * che riguardano la visualizzazione di un determinato elemento
 * della mappa del comune
 */
public class VisualizeController {
    private MappaComune mappaComune;

    public VisualizeController() {
        POI poi = new POI("Fontana dei piccioni","Fontano ndo cagano i piccioni",
                new Coordinate(36,36));
        ArrayList<POI> arrayList = new ArrayList<>();
        arrayList.add(poi);
        this.mappaComune = new MappaComune(arrayList,new ArrayList<>());
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
    public ArrayList<POI> filter(String conditionFilter){
        return switch (conditionFilter){
            case "1" -> this.mappaComune.getListaPoi();
            //case "2" -> this.mappaComune.getListaItinerari(); DA FARE
            default -> null;
        };
    }

}
