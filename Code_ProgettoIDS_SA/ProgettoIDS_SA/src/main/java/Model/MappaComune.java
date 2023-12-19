package Model;

import java.util.ArrayList;

/**
 * Questa classe ha la responsabilità di gestire la mappa del
 * comune di riferimento e le sue operazioni, come la visualizzazione
 * dei POI e itinerari, tener traccia della loro posizioni e
 * dei luoghi a cui si riferiscono
 */

public class MappaComune implements MapInterface{

    private ArrayList<POI> listaPoi;
    private ArrayList<String> listaItinerari;

    public MappaComune(ArrayList<POI> listaPoi, ArrayList<String> listaItinerari) {
        this.listaPoi = listaPoi;
        this.listaItinerari = listaItinerari;
    }

    public ArrayList<POI> getListaPoi() {
        return listaPoi;
    }

    public ArrayList<String> getListaItinerari() {
        return listaItinerari;
    }

    public void setListaPoi(ArrayList<POI> listaPoi) {
        this.listaPoi = listaPoi;
    }

    public void setListaItinerari(ArrayList<String> listaItinerari) {
        this.listaItinerari = listaItinerari;
    }

    public boolean isInMap(POI newPoi){
        for (POI poi : this.listaPoi) {
            if(poi.getPosizione().equals(newPoi.getPosizione()))
                return true;
        }
        return false;
    }

    /**
     * Ottiene uno specifico POI della mappa, in base al nome
     * passatogli.
     *
     * @param nomePoi il nome del POI da cercare
     * @return il POI se esiste, null altrimenti
     */
    public POI obtainSpecificPOI(String nomePoi){
        for (POI poi: this.listaPoi ) {
            if(poi.getNome().equals(nomePoi))
                return poi;
        }
        return null;
    }


}
