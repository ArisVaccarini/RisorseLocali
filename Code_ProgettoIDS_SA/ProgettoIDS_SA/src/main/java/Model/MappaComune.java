package Model;

import Database.DBManager;
import Recources.StatusElement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questa classe ha la responsabilità di gestire la mappa del
 * comune di riferimento e le sue operazioni, come la visualizzazione
 * dei POI e itinerari, tener traccia della loro posizioni e
 * dei luoghi a cui si riferiscono
 */

public class MappaComune implements MapInterface{
    private static MappaComune mappaComune;
    private HashMap<String, ElementiMappa> elementiMappa;

    public MappaComune() {
        this.elementiMappa = DBManager.getInstance().selectAllPOI();
        HashMap<String,ElementiMappa> itinerari = DBManager.getInstance().selectAllItinerari();
        this.elementiMappa.putAll(itinerari);
    }

    public HashMap<String, ElementiMappa> getElementiMappa() {
        return this.elementiMappa;
    }

    /**
     * Ritorna l'istanza della mappa del comune attiva, se non esiste
     * ne crea una nuova
     *
     * @return la mappa del comune
     */
    public static MappaComune getMap() {
        if(mappaComune == null)
            mappaComune = new MappaComune();
        return mappaComune;
    }

    public boolean isInMap(ElementiMappa newPoi){
        if (this.elementiMappa.isEmpty())
            return false;
        for (ElementiMappa element : this.getElementiMappa().values()) {
            if(element.equals(newPoi))
                return true;
        }
        return false;
    }

    @Override
    public ArrayList<? extends ElementiMappa> getTypedComponent(Class<? extends ElementiMappa> element) {
        if(this.elementiMappa.isEmpty())
            return null;
        ArrayList<ElementiMappa> list = new ArrayList<>();
        for (ElementiMappa singleComponent : this.elementiMappa.values()) {
            if(singleComponent.getClass().equals(element) &&
                    singleComponent.getStatus().equals(StatusElement.VISIBLE))
                list.add(singleComponent);
        }
        return list;
    }

    public ElementiMappa obtainSpecificElement(String idElement){
        if(idElement == null || this.elementiMappa.get(idElement) == null)
            return null;
        return this.elementiMappa.get(idElement);
    }


}
